package com.kokk.application.concert.port.out.messaging;

import com.kokk.domain.event.ConcertReservedEvent;

public interface KafkaMessageProducerPort {
  void publish(ConcertReservedEvent event);
}
