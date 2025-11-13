package com.kokk.application.concert.dto.response;

import com.kokk.domain.model.entity.ConcertSessionSeat;
import io.swagger.v3.oas.annotations.media.Schema;

public record ConcertSessionSeatResponseDto(
        @Schema(name = "좌석 번호")
        String SeatNum,
        @Schema(name = "좌석 금액")
        Long seatPrice
) {

  public static ConcertSessionSeatResponseDto from(ConcertSessionSeat seat) {
    return new ConcertSessionSeatResponseDto(
            seat.getSeat().toSeatNum(),
            seat.getPrice()
    );
  }
}
