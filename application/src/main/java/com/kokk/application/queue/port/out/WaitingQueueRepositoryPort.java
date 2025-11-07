package com.kokk.application.queue.port.out;

import com.kokk.domain.model.valueObject.WaitingQueue;

public interface WaitingQueueRepositoryPort {

  WaitingQueue createWaitingQueue(WaitingQueue waitingQueue);

  WaitingQueue findByToken(String token);
}
