package com.kokk.application.concert.dto.response;

import com.kokk.domain.model.entity.ConcertSessionSeat;

public record ConcertSessionSeatResponseDto(

        String SeatNum,
        Long seatPrice
) {

  public static ConcertSessionSeatResponseDto from(ConcertSessionSeat seat) {
    return new ConcertSessionSeatResponseDto(
            seat.getSeat().toSeatNum(),
            seat.getPrice()
    );
  }
}
