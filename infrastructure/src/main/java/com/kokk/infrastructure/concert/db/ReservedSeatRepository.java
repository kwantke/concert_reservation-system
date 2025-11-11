package com.kokk.infrastructure.concert.db;

import com.kokk.domain.model.entity.ReservedSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservedSeatRepository extends JpaRepository<ReservedSeat, Long> {

}
