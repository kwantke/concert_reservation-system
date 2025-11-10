package com.kokk.application.concert.port.in;

import com.kokk.application.concert.dto.request.ReserveConcertRequest;

public interface ReservationServicePort {

  public void reserveConcert(ReserveConcertRequest request);
}
