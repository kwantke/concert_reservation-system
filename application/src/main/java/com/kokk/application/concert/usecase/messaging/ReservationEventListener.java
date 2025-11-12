package com.kokk.application.concert.usecase.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kokk.application.concert.port.in.OutboxServicePort;

import com.kokk.application.concert.port.out.messaging.KafkaMessageProducerPort;
import com.kokk.domain.event.ConcertReservedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationEventListener {
  private final OutboxServicePort outboxServicePort;
  private final KafkaMessageProducerPort kafkaMessageProducerPort;

  @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
  public void saveOutbox(final ConcertReservedEvent event) throws JsonProcessingException {
    outboxServicePort.saveOutbox(event);
  }


  @Async
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handle(final ConcertReservedEvent event) {
    kafkaMessageProducerPort.publish(event);
  }

}
