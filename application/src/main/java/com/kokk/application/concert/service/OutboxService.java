package com.kokk.application.concert.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kokk.application.concert.port.in.OutboxServicePort;
import com.kokk.application.concert.port.out.OutboxRepositoryPort;
import com.kokk.domain.event.ConcertReservedEvent;
import com.kokk.domain.model.entity.Outbox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OutboxService implements OutboxServicePort {

  private final OutboxRepositoryPort outboxRepositoryPort;
  private final ObjectMapper objectMapper;

  @Override
  public void saveOutbox(ConcertReservedEvent event) throws JsonProcessingException {
    final String payloadJson = objectMapper.writeValueAsString(event);
    Outbox outbox = Outbox.of(event.getClass().getSimpleName(), event.getEventKey(), payloadJson);

    outboxRepositoryPort.save(outbox);
  }

  @Override
  public void publish(String eventKey) {
    final Outbox outbox = outboxRepositoryPort.getByEventKey(eventKey);
    outbox.publish();
    outboxRepositoryPort.save(outbox);

  }


}
