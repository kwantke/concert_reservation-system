package com.kokk.application.concert.dto.response;

import com.kokk.domain.model.entity.ConcertSession;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public record ConcertSessionResponseDto(

        @Schema(name = "시즌 ID")
        Long sessionId,
        @Schema(name = "시작일시")
        LocalDateTime startDate
) {

        public static List<ConcertSessionResponseDto> from(List<ConcertSession> concertSessions) {
                return concertSessions.stream()
                        .map(session -> new ConcertSessionResponseDto(
                                session.getId(),
                                session.getStartDate()
                        ))
                        .toList();
        }
}
