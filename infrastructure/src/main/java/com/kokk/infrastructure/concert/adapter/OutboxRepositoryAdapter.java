package com.kokk.infrastructure.concert.adapter;

import com.kokk.application.concert.port.out.OutboxRepositoryPort;
import com.kokk.domain.model.entity.Outbox;
import com.kokk.infrastructure.concert.db.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OutboxRepositoryAdapter implements OutboxRepositoryPort {
  private final OutboxRepository outboxRepository;
  @Override
  public void save(Outbox outbox) {
    outboxRepository.save(outbox);
  }

  @Override
  public Outbox getByEventKey(String eventKey) {
    return outboxRepository.findByEventKey(eventKey);
  }

}
