package com.kokk.infrastructure.concert.db;

import com.kokk.domain.model.entity.ConcertSessionSeat;
import com.kokk.infrastructure.concert.db.custom.CustomConcertSessionSeatRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcertSessionSeatRepository extends JpaRepository<ConcertSessionSeat, Long>, CustomConcertSessionSeatRepository {
  List<ConcertSessionSeat> findByConcertSessionIdAndSeatIdInAndReservedFalse(Long concertSessionId, List<Long> seatIds);
}
