package com.kokk.application.queue.usecase;

import com.kokk.application.common.UseCase;
import com.kokk.application.queue.dto.response.GetWaitingQueueResponseDto;
import com.kokk.application.queue.port.in.WaitingQueueServicePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class GetWaitingQueueUseCase {

  private final WaitingQueueServicePort waitingQueueServicePort;

  public GetWaitingQueueResponseDto getWaitingQueue(String token) {
    return GetWaitingQueueResponseDto.of(waitingQueueServicePort.getWaitingQueue(token));
  }
}
