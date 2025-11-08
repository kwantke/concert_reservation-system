package com.kokk.application.concert.service;

import com.kokk.application.concert.port.in.ConcertServicePort;
import com.kokk.application.concert.port.out.ConcertSessionRepositoryPort;
import com.kokk.domain.model.entity.ConcertSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConcertService implements ConcertServicePort {

  private final ConcertSessionRepositoryPort concertSessionRepositoryPort;

  @Override
  public List<ConcertSession> getAvailableConcertSessions(Long concertId) {
    return concertSessionRepositoryPort.findByConcertId(concertId);
  }
}
