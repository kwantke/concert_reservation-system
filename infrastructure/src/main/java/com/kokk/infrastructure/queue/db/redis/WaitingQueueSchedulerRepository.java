package com.kokk.infrastructure.queue.db.redis;

public interface WaitingQueueSchedulerRepository {
  int batchActivate(int count);
}
