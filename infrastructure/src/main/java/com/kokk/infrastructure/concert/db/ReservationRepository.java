package com.kokk.infrastructure.concert.db;

import com.kokk.domain.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
