package com.kokk.infrastructure.queue.adapter;

import com.kokk.application.queue.port.out.WaitingQueueRepositoryPort;
import com.kokk.domain.model.valueObject.WaitingQueue;
import com.kokk.infrastructure.queue.db.redis.WaitingQueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class WaitingQueueRepositoryAdapter implements WaitingQueueRepositoryPort {

  private final WaitingQueueRepository waitingQueueRepository;
  @Override
  public WaitingQueue createWaitingQueue(WaitingQueue waitingQueue) {

    return waitingQueueRepository.createWaitingQueue(waitingQueue);
  }

  @Override
  public WaitingQueue findByToken(String token) {
    return waitingQueueRepository.findByToken(token);
  }

}
