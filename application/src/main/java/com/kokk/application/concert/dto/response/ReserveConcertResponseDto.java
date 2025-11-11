package com.kokk.application.concert.dto.response;

import com.kokk.domain.model.entity.Reservation;
import com.kokk.domain.enums.ReservationStatus;

public record ReserveConcertResponseDto(
        Long concertSessionId,
        Long userId,
        Long totalPrice,
        String status

) {
  public static ReserveConcertResponseDto from(Reservation reservation) {
    return new ReserveConcertResponseDto(
            reservation.getConcertSessionId(),
            reservation.getUserId(),
            reservation.getTotalPrice(),
            ReservationStatus.fromDbValue(reservation.getStatus()).getStatusName()
    );
  }
}
