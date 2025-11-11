package com.kokk.application.concert.port.in;

import com.kokk.application.concert.dto.request.ReserveConcertRequest;
import com.kokk.application.concert.dto.response.ReserveConcertResponseDto;
import com.kokk.domain.model.entity.Reservation;

public interface ReservationServicePort {

  public ReserveConcertResponseDto reserveConcert(ReserveConcertRequest request);

  Reservation getReservation(Long aLong);

  void updateReservation(Reservation reservation);
}
