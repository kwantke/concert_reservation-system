package com.kokk.application.concert.service;

import com.kokk.application.concert.port.in.ConcertServicePort;
import com.kokk.application.concert.port.out.ConcertSessionRepositoryPort;
import com.kokk.application.concert.port.out.ConcertSessionSeatRepositoryPort;
import com.kokk.domain.model.entity.ConcertSession;
import com.kokk.domain.model.entity.ConcertSessionSeat;
import com.kokk.domain.model.entity.ReservedSeat;
import com.kokk.domain.model.valueObject.CustomConcertSessionSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConcertService implements ConcertServicePort {

  private final ConcertSessionRepositoryPort concertSessionRepositoryPort;

  private final ConcertSessionSeatRepositoryPort concertSessionSeatRepositoryPort;

  @Override
  public List<ConcertSession> getAvailableConcertSessions(Long concertId) {
    return concertSessionRepositoryPort.findByConcertId(concertId);
  }

  @Override
  public List<CustomConcertSessionSeat> findConcertSessionSeatsByConcertSessionId(Long concertSessionId) {
    return concertSessionSeatRepositoryPort.findConcertSessionSeatsByConcertSessionId(concertSessionId);
  }

  @Override
  public void cancelTemporaryReservation(List<ReservedSeat> reservedSeats) {
    reservedSeats.forEach(reservedSeat -> {
        ConcertSessionSeat concertSessionSeat = reservedSeat.getConcertSessionSeat();
        concertSessionSeat.updateReservedFalse();
        concertSessionSeatRepositoryPort.save(concertSessionSeat);

    });
  }
}
