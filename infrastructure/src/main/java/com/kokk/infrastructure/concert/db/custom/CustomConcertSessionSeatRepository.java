package com.kokk.infrastructure.concert.db.custom;

import com.kokk.domain.model.valueObject.CustomConcertSessionSeat;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CustomConcertSessionSeatRepository {
  List<CustomConcertSessionSeat> findConcertSessionSeatsByConcertSessionId(Long concertSessionId, Sort sort);
}
