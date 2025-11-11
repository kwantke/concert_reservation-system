package com.kokk.application.concert.dto.response;

import com.kokk.domain.model.entity.ConcertSessionSeat;
import com.kokk.domain.model.entity.Reservation;
import com.kokk.domain.enums.ReservationStatus;
import com.kokk.domain.model.entity.ReservedSeat;

import java.util.List;

public record ReserveConcertResponseDto(
        Long concertSessionId,
        Long userId,
        Long totalPrice,
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
