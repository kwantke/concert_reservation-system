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

  private static final String WAITING_QUEUE_KEY = "waiting-queues";
  private static final String ACTIVE_QUEUE_KEY = "active-queues";
  private static final String TOKEN_META_KEY_PREFIX = "token-meta:";

  private static final long ACTIVE_QUEUE_TTL_SECONDS = 300; // 5분

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
    redisTemplate.opsForHash().put(metaKey(token), "userId",String.valueOf(waitingQueue.getUserId()));

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

  private String metaKey(final String token) {
    return TOKEN_META_KEY_PREFIX + token;
  }

  private Long findWaitingRank(final String token) {
    return redisTemplate.opsForZSet().rank(WAITING_QUEUE_KEY, token);
  }

  private boolean isActive(final String token) {
    boolean member = redisTemplate.opsForSet().isMember(ACTIVE_QUEUE_KEY, token);
    return Boolean.TRUE.equals(member);
  }

  //토큰의 userId를 가져오고, 없으면 도메인 예외
  private long getUserIdOrThrow(final String token) {
    String userId = (String) redisTemplate.opsForHash().get(metaKey(token), "userId");
    if (userId == null) {
      throw new CoreException(WaitingQueueErrorCode.INVALID_WAITING_QUEUE);
    }
    return Long.parseLong(userId);
  }

  private WaitingQueue buildWaitingQueue(String token, Long waitingRank, QueueStatus queueStatus) {
    long userId = getUserIdOrThrow(token);
    return WaitingQueue.getWaitingQueue(userId, token, waitingRank, queueStatus);
  }
}
