package com.kokk.application.concert.port.in;

import com.kokk.domain.model.entity.ConcertSession;
import com.kokk.domain.model.valueObject.CustomConcertSessionSeat;

import java.util.List;

public interface ConcertServicePort {


  List<ConcertSession> getAvailableConcertSessions(Long concertId);

  List<CustomConcertSessionSeat> findConcertSessionSeatsByConcertSessionId(Long concertSessionId);
}
