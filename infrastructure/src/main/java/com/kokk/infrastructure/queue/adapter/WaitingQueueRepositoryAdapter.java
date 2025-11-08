package com.kokk.infrastructure.queue.adapter;

import com.kokk.application.queue.port.out.WaitingQueueRepositoryPort;
import com.kokk.domain.model.valueObject.WaitingQueue;
import com.kokk.infrastructure.queue.db.redis.WaitingQueueRepository;
import com.kokk.infrastructure.queue.db.redis.WaitingQueueSchedulerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class WaitingQueueRepositoryAdapter implements WaitingQueueRepositoryPort {

  private final WaitingQueueRepository waitingQueueRepository;

  private final WaitingQueueSchedulerRepository waitingQueueSchedulerRepository;
  @Override
  public WaitingQueue createWaitingQueue(WaitingQueue waitingQueue) {

    return waitingQueueRepository.createWaitingQueue(waitingQueue);
  }

  @Override
  public WaitingQueue findByToken(String token) {
    return waitingQueueRepository.findByToken(token);
  }

  @Override
  public void checkActivatedQueueByToken(String token) {
    waitingQueueRepository.checkActivatedQueueByToken(token);
  }

  @Override
  public int batchActivate(int count) {
    return waitingQueueSchedulerRepository.batchActivate(count);
  }

}
