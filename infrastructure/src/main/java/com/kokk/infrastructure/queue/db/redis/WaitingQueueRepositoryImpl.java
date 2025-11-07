package com.kokk.infrastructure.queue.db.redis;

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
  private static final String TOKEN_META_KEY_PREFIX = "token-meta";

  private static final long ACTIVE_QUEUE_TTL_SECONDS = 300; // 5분

  @Override
  public WaitingQueue createWaitingQueue(WaitingQueue waitingQueue) {
    final String token = waitingQueue.getToken();
    long timestamp = Timestamp.valueOf(waitingQueue.getCreatedAt()).getTime();

    // 대기열 생성
    redisTemplate.opsForZSet().add(WAITING_QUEUE_KEY, token, timestamp); // 순번 확인이 필요하므로 ZSet 사용
    // 대기열 메타 정보 저장 예) HSET token-meta:{token} userId "12345"
    redisTemplate.opsForHash().put(TOKEN_META_KEY_PREFIX + token, "userId",String.valueOf(waitingQueue.getUserId()));

    return waitingQueue;
  }
}
