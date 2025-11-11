package com.kokk.application.queue.usecase;


import com.kokk.application.common.UseCase;
import com.kokk.application.queue.dto.response.CreateWaitingQueueResponseDto;
import com.kokk.application.queue.port.in.WaitingQueueServicePort;
import com.kokk.domain.model.valueObject.WaitingQueue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class CreateWaitingQueueUseCase {

  private final WaitingQueueServicePort waitingQueueServicePort;


  public CreateWaitingQueueResponseDto createWaitingQueue(final Long userId) {
    WaitingQueue waitingQueue = waitingQueueServicePort.createWaitingQueue(userId);
    return CreateWaitingQueueResponseDto.of(waitingQueue);
  }

}
