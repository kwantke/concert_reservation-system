package com.kokk.infrastructure.concert.db;

import com.kokk.domain.enums.ReservationStatus;
import com.kokk.domain.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  Optional<Reservation> findByIdAndStatus(Long reservationId, ReservationStatus status);
}
