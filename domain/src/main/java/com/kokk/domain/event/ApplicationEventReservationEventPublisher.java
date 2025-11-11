package com.kokk.domain.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationEventReservationEventPublisher implements ReservationEventPublisher{

  // 내부 이벤트를 발행하기 위한 인터페이스
  private final ApplicationEventPublisher applicationEventPublisher;
  @Override
  public void publish(ConcertReservedEvent event) {
    applicationEventPublisher.publishEvent(event);
  }
}
