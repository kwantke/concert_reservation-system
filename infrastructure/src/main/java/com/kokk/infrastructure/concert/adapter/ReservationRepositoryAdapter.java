package com.kokk.infrastructure.concert.adapter;

import com.kokk.application.concert.port.out.ReservationRepositoryPort;
import com.kokk.domain.model.entity.Reservation;
import com.kokk.infrastructure.concert.db.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReservationRepositoryAdapter implements ReservationRepositoryPort {
  private final ReservationRepository reservationRepository;

  @Override
  public Reservation save(Reservation reservation) {
    return reservationRepository.save(reservation);
  }
}
