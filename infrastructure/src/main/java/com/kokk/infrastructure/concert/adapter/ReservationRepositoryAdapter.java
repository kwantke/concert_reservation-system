package com.kokk.infrastructure.concert.adapter;

import com.kokk.application.concert.port.out.ReservationRepositoryPort;
import com.kokk.domain.enums.ReservationStatus;
import com.kokk.domain.model.entity.Reservation;
import com.kokk.infrastructure.concert.db.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ReservationRepositoryAdapter implements ReservationRepositoryPort {
  private final ReservationRepository reservationRepository;

  @Override
  public Reservation save(Reservation reservation) {
    return reservationRepository.save(reservation);
  }

  @Override
  public Optional<Reservation> findById(Long reservationId) {
    return reservationRepository.findById(reservationId);
  }
}
