package com.kokk.application.concert.port.in;

import com.kokk.application.concert.dto.request.ReserveConcertRequest;
import com.kokk.application.concert.dto.response.ReserveConcertResponseDto;
import com.kokk.domain.model.entity.Reservation;

import java.util.List;

public interface ReservationServicePort {

  public ReserveConcertResponseDto reserveConcert(ReserveConcertRequest request);

  Reservation getReservation(Long aLong);

  void updateReservation(Reservation reservation);

  List<Reservation> getTemporaryReservationToBeExpired(int minutes);

  void cancelTemporaryReservation(Reservation reservation);
}
