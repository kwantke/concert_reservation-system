package com.kokk.application.concert.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kokk.application.concert.port.in.OutboxServicePort;
import com.kokk.application.kafka.KafkaMessageProducer;
import com.kokk.domain.event.ConcertReservedEvent;
import com.kokk.domain.event.ReservationEventPublisher;
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
  private final KafkaMessageProducer kafkaMessageProducer;

  @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
  public void saveOutbox(final ConcertReservedEvent event) throws JsonProcessingException {
    outboxServicePort.saveOutbox(event);
  }


  @Async
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handle(final ConcertReservedEvent event) {
    kafkaMessageProducer.publish(event);
  }

}
