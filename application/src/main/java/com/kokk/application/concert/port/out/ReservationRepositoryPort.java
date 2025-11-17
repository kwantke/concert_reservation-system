package com.kokk.application.concert.port.out;

import com.kokk.domain.enums.ReservationStatus;
import com.kokk.domain.model.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepositoryPort {

  Reservation save(Reservation reservation);

  Optional<Reservation> findById(Long reservationId);

  List<Reservation> findByTemporaryReservationToBeExpired(int minutes);
}
