package com.kokk.application.concert.usecase;

import com.kokk.application.common.UseCase;
import com.kokk.application.concert.dto.request.ReserveConcertRequest;
import com.kokk.application.concert.dto.response.ReserveConcertResponseDto;
import com.kokk.application.concert.port.in.ReservationServicePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class ReserveConcertUseCase {

  private final ReservationServicePort reservationServicePort;
  public ReserveConcertResponseDto reserveConcert(ReserveConcertRequest request) {
    return reservationServicePort.reserveConcert(request);
  }
}
