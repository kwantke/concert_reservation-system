package com.kokk.infrastructure.queue.db.redis;

import com.kokk.domain.model.enums.QueueStatus;
import com.kokk.domain.model.exception.CoreException;
import com.kokk.domain.model.exception.queue.WaitingQueueErrorCode;
import com.kokk.domain.model.valueObject.WaitingQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
@RequiredArgsConstructor
public class WaitingQueueRepositoryImpl implements WaitingQueueRepository {

  private final RedisTemplate<String, Object> redisTemplate;

  static final String WAITING_QUEUE_KEY = "waiting-queues";
  static final String ACTIVE_QUEUE_KEY = "active-queues";
  static final String TOKEN_META_KEY_PREFIX = "token-meta:";
  private static final String HASH_KEY = "userId";
  static final long ACTIVE_QUEUE_TTL_SECONDS = 300; // 5분

  /**
   * Redis 대기열 생성
  * */
  @Override
  public WaitingQueue createWaitingQueue(WaitingQueue waitingQueue) {
    final String token = waitingQueue.getToken();
    long timestamp = Timestamp.valueOf(waitingQueue.getCreatedAt()).getTime();

    // 대기열 생성, 순번 확인이 필요하므로 ZSet 사용
    redisTemplate.opsForZSet().add(WAITING_QUEUE_KEY, token, timestamp);

    // 대기열 메타 정보 저장 예) HSET token-meta:{token} userId "12345"
    redisTemplate.opsForHash().put(metaKey(token), HASH_KEY,String.valueOf(waitingQueue.getUserId()));

    return waitingQueue;
  }

  /**
   * Redis 대기열 조회
   * */
  @Override
  public WaitingQueue findByToken(String token) {

    // 대기열 체크
    Long waitingRank = findWaitingRank(token);
    if (waitingRank != null) {
      return buildWaitingQueue(token, waitingRank, QueueStatus.WAITING);
    }

    // 대기열 활성화 체크
    if (isActive(token)) {
      return buildWaitingQueue(token, 0L, QueueStatus.ACTIVATED);
    }

    throw new CoreException(WaitingQueueErrorCode.INVALID_WAITING_QUEUE);
  }

  @Override
  public void checkActivatedQueueByToken(String token) {
    if (!isActive(token)) {
      throw new CoreException(WaitingQueueErrorCode.INVALID_ACTIVATED_QUEUE);
    }

  }

  /** 매타 키 설정 */
  private String metaKey(final String token) {
    return TOKEN_META_KEY_PREFIX + token;
  }

  /** 대기열 토큰 순위 조회 */
  private Long findWaitingRank(final String token) {
    return redisTemplate.opsForZSet().rank(WAITING_QUEUE_KEY, token);
  }

  /** 활성화 토큰 여부 조회 */
  private boolean isActive(final String token) {
    boolean member = redisTemplate.opsForSet().isMember(ACTIVE_QUEUE_KEY, token);
    return Boolean.TRUE.equals(member);
  }

  /** 토큰 매타 정보 여부 조회 */
  //토큰의 userId를 가져오고, 없으면 도메인 예외
  private long getUserIdOrThrow(final String token) {
    String userId = (String) redisTemplate.opsForHash().get(metaKey(token), HASH_KEY);
    if (userId == null) {
      throw new CoreException(WaitingQueueErrorCode.INVALID_WAITING_QUEUE);
    }
    return Long.parseLong(userId);
  }

  /** 토큰으로 사용자 조회 후 WaitingQueue 객체 생성 */
  private WaitingQueue buildWaitingQueue(String token, Long waitingRank, QueueStatus queueStatus) {
    long userId = getUserIdOrThrow(token);
    return WaitingQueue.getWaitingQueue(userId, token, waitingRank, queueStatus);
  }
}
