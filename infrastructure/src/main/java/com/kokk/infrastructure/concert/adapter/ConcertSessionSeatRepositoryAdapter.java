package com.kokk.infrastructure.concert.adapter;

import com.kokk.application.concert.port.out.ConcertSessionSeatRepositoryPort;
import com.kokk.domain.model.entity.ConcertSessionSeat;
import com.kokk.domain.model.valueObject.CustomConcertSessionSeat;
import com.kokk.infrastructure.concert.db.ConcertSessionSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ConcertSessionSeatRepositoryAdapter implements ConcertSessionSeatRepositoryPort {

  private final ConcertSessionSeatRepository concertSessionSeatRepository;

  public List<CustomConcertSessionSeat> findConcertSessionSeatsByConcertSessionId(Long concertSessionId) {
    Sort sort = Sort.by(
            new Sort.Order(Sort.Direction.ASC, "seatRow"),
            new Sort.Order(Sort.Direction.ASC, "seatColumn")
    );
    return concertSessionSeatRepository.findConcertSessionSeatsByConcertSessionId(concertSessionId, sort);
  }

  @Override
  public List<ConcertSessionSeat> findByConcertSessionIdAndSeatIdInAndReservedFalse(Long concertSessionId, List<Long> seatIds) {
    return concertSessionSeatRepository.findByConcertSessionIdAndSeatIdInAndReservedFalse(concertSessionId, seatIds);
  }

  @Override
  public void saveAll(List<ConcertSessionSeat> concertSessionSeats) {
    concertSessionSeatRepository.saveAll(concertSessionSeats);
  }

  @Override
  public long countReservedSeats(Long screeningId) {
    return concertSessionSeatRepository.countByConcertSessionIdAndReservedIsTrue(screeningId);
  }
}
