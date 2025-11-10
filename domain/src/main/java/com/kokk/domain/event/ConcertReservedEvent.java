package com.kokk.domain.event;

import com.kokk.domain.model.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor(force = true)
public class ConcertReservedEvent extends DomainEvent{
  private final String reservationId;
  private final Reservation reservation;
}
