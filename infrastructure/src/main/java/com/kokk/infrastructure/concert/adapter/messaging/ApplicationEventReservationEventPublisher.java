package com.kokk.infrastructure.concert.adapter.messaging;

import com.kokk.application.concert.port.out.messaging.ReservationEventPublisherPort;
import com.kokk.domain.event.ConcertReservedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationEventReservationEventPublisher implements ReservationEventPublisherPort {

  // 내부 이벤트를 발행하기 위한 인터페이스
  private final ApplicationEventPublisher applicationEventPublisher;
  @Override
  public void publish(ConcertReservedEvent event) {
    applicationEventPublisher.publishEvent(event);
  }
}
