package com.kokk.application.concert.port.in;

import com.kokk.application.concert.dto.request.ReserveConcertRequest;
import com.kokk.application.concert.dto.response.ReserveConcertResponseDto;

public interface ReservationServicePort {

  public ReserveConcertResponseDto reserveConcert(ReserveConcertRequest request);
}
