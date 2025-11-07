package com.kokk.application.queue.port.in;

import com.kokk.domain.model.valueObject.WaitingQueue;

public interface WaitingQueueServicePort {
  WaitingQueue createWaitingQueue(final Long userId);
}
