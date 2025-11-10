package com.kokk.application.concert.port.out;

import com.kokk.domain.model.entity.ConcertSessionSeat;
import com.kokk.domain.model.valueObject.CustomConcertSessionSeat;

import java.util.List;

public interface ConcertSessionSeatRepositoryPort {
  List<CustomConcertSessionSeat> findConcertSessionSeatsByConcertSessionId(Long concertSessionId);

  List<ConcertSessionSeat> findByConcertSessionIdAndSeatIdInAndReservedFalse(Long concertSessionId, List<Long> seatIds);

  void saveAll(List<ConcertSessionSeat> concertSessionSeats);
}
