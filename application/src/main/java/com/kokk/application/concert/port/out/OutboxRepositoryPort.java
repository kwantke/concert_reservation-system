package com.kokk.application.concert.port.out;

import com.kokk.domain.model.entity.Outbox;

public interface OutboxRepositoryPort {
  void save(Outbox outbox);

  Outbox getByEventKey(String eventKey);
}
