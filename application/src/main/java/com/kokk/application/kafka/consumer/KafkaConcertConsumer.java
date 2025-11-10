package com.kokk.application.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kokk.application.concert.port.in.OutboxServicePort;
import com.kokk.domain.event.ConcertReservedEvent;
import com.kokk.domain.model.enums.OutboxStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class KafkaConcertConsumer {

  private final OutboxServicePort outboxServicePort;

  @KafkaListener(topics = "${spring.kafka.topic.reserve}")
  public void consumeOutbox(ConcertReservedEvent event, Acknowledgment ack) throws JsonProcessingException {
    outboxServicePort.publish(event.getEventKey());
    log.info("Consumer the event{}", event);
    ack.acknowledge();
  }
}
