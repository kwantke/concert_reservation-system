package com.kokk.infrastructure.queue.db.redis;

import com.kokk.domain.model.valueObject.WaitingQueue;

public interface WaitingQueueRepository {
  WaitingQueue createWaitingQueue(WaitingQueue waitingQueue);

  WaitingQueue findByToken(final String token);

  void checkActivatedQueueByToken(String token);

  void removeActiveQueue(String token);
}
