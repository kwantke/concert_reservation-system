package com.kokk.application.concert.dto.response;

import com.kokk.domain.model.entity.ConcertSession;
import com.kokk.domain.model.valueObject.CustomConcertSessionSeat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public record CustomConcertSessionSeatResponseDto(

        @Schema(name = "시즌좌석ID")
        Long id,
        @Schema(name = "시즌ID")
        Long concertSessionId,
        @Schema(name = "좌석ID")
        Long seatId,
        @Schema(name = "좌석 번호")
        String seatNum,
        @Schema(name = "좌석 예약 여부")
        boolean reserved
) {

        public static List<CustomConcertSessionSeatResponseDto> from(List<CustomConcertSessionSeat> concertSessions) {
                return concertSessions.stream()
                        .map(session -> new CustomConcertSessionSeatResponseDto(
                                session.getId(),
                                session.getConcertSessionId(),
                                session.getSeatId(),
                                session.seatRowAndColumnToString(),
                                session.isReserved()
                        ))
                        .toList();
        }
}
