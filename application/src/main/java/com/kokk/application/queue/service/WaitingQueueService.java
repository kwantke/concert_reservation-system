package com.kokk.application.queue.service;

import com.kokk.application.queue.dto.response.GetWaitingQueueResponseDto;
import com.kokk.application.queue.port.in.WaitingQueueServicePort;
import com.kokk.application.queue.port.out.WaitingQueueRepositoryPort;
import com.kokk.domain.model.provider.time.TimeProvider;
import com.kokk.domain.model.provider.uuid.UUIDGenerator;
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
  public WaitingQueue getWaitingQueue(String token) {
    return waitingQueueRepositoryPort.findByToken(token);
  }
}
