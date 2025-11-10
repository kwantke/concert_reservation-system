package com.kokk.application.concert.port.out;

import com.kokk.domain.model.entity.Reservation;

public interface ReservationRepositoryPort {

  Reservation save(Reservation reservation);
}
