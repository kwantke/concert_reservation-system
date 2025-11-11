package com.kokk.application.concert.port.out;

import com.kokk.domain.model.entity.ReservedSeat;

public interface ReservedSeatRepositoryPort {
  void save(ReservedSeat reservedSeat);
}
