package com.kokk.infrastructure.concert.adapter;

import com.kokk.application.concert.port.out.ReservedSeatRepositoryPort;
import com.kokk.domain.model.entity.ReservedSeat;
import com.kokk.infrastructure.concert.db.ReservedSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReservedSeatRepositoryAdapter implements ReservedSeatRepositoryPort {

  private final ReservedSeatRepository reservedSeatRepository;
  @Override
  public void save(ReservedSeat reservedSeat) {
    reservedSeatRepository.save(reservedSeat);
  }
}
