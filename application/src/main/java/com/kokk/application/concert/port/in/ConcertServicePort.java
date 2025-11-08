package com.kokk.application.concert.port.in;

import com.kokk.domain.model.entity.ConcertSession;

import java.util.List;

public interface ConcertServicePort {


  List<ConcertSession> getAvailableConcertSessions(Long concertId);
}
