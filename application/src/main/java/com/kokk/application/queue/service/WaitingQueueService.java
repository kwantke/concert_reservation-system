package com.kokk.application.queue.service;

import com.kokk.application.queue.port.in.WaitingQueueServicePort;
import com.kokk.application.queue.port.out.WaitingQueueRepositoryPort;
import com.kokk.domain.provider.time.TimeProvider;
import com.kokk.domain.provider.uuid.UUIDGenerator;
import com.kokk.domain.model.valueObject.WaitingQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WaitingQueueService implements WaitingQueueServicePort {

  private final WaitingQueueRepositoryPort waitingQueueRepositoryPort;

  private final UUIDGenerator uuidGenerator;

  private final TimeProvider timeProvider;


  public WaitingQueue createWaitingQueue(final Long userId) {
    final String token = uuidGenerator.generate();

    return waitingQueueRepositoryPort.createWaitingQueue(WaitingQueue.of(userId, token, timeProvider.now()));
  }

  @Override
  public WaitingQueue getWaitingQueue(final String token) {
    return waitingQueueRepositoryPort.findByToken(token);
  }

  @Override
  public void checkActivatedQueue(final String token) {
    waitingQueueRepositoryPort.checkActivatedQueueByToken(token);
  }

  @Override
  public int batchActivate(int count) {
    return waitingQueueRepositoryPort.batchActivate(count);
  }

  @Override
  public void expireActiveQueue(String token) {
    waitingQueueRepositoryPort.removeActiveQueue(token);
  }
}
