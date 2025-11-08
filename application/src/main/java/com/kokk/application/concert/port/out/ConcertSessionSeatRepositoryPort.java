package com.kokk.application.concert.port.out;

import com.kokk.domain.model.valueObject.CustomConcertSessionSeat;

import java.util.List;

public interface ConcertSessionSeatRepositoryPort {
  List<CustomConcertSessionSeat> findConcertSessionSeatsByConcertSessionId(Long concertSessionId);
}
