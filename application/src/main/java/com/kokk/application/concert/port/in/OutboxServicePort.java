package com.kokk.application.concert.port.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kokk.domain.event.ConcertReservedEvent;

public interface OutboxServicePort {
  void saveOutbox(ConcertReservedEvent event) throws JsonProcessingException;

  void publish(String eventKey);

}
