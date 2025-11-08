package com.kokk.application.queue.port.in;

import com.kokk.application.queue.dto.response.GetWaitingQueueResponseDto;
import com.kokk.domain.model.valueObject.WaitingQueue;

public interface WaitingQueueServicePort {
  WaitingQueue createWaitingQueue(final Long userId);

  WaitingQueue getWaitingQueue(final String token);

  void checkActivatedQueue(final String token);
}
