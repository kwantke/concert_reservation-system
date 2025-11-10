package com.kokk.domain.event;

public interface ReservationEventPublisher {
  void publish(ConcertReservedEvent event);
}
