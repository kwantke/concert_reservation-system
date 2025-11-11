package com.kokk.infrastructure.concert.db;

import com.kokk.domain.model.entity.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxRepository extends JpaRepository<Outbox, Long> {
  Outbox findByEventKey(String eventKey);
}
