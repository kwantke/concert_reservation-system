package com.kokk.application.concert.dto.response;

import com.kokk.domain.model.entity.ConcertSessionSeat;
import com.kokk.domain.model.entity.Reservation;
import com.kokk.domain.enums.ReservationStatus;
import com.kokk.domain.model.entity.ReservedSeat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ReserveConcertResponseDto(
        @Schema(name = "콘서트 시즌 ID")
        Long concertSessionId,
        @Schema(name = "사용자 ID")
        Long userId,
        @Schema(name = "총 금액")
        Long totalPrice,
        @Schema(name = "예약 상태")
        String status,
        List<ConcertSessionSeatResponseDto> reservedSeats

) {
  public static ReserveConcertResponseDto from(Reservation reservation, List<ConcertSessionSeat> concertSessionSeats) {
    return new ReserveConcertResponseDto(
            reservation.getConcertSessionId(),
            reservation.getUserId(),
            reservation.getTotalPrice(),
            reservation.getStatus().getStatusName(),
            concertSessionSeats.stream().
                    map(ConcertSessionSeatResponseDto::from)
                    .toList()
    );
  }
}
