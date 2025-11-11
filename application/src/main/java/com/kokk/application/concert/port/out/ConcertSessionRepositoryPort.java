package com.kokk.application.concert.port.out;

import com.kokk.domain.model.entity.ConcertSession;

import java.util.List;

public interface ConcertSessionRepositoryPort {
  List<ConcertSession> findByConcertId(Long concertId);
}
