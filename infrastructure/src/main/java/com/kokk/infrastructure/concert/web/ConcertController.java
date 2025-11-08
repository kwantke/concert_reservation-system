package com.kokk.infrastructure.concert.web;

import com.kokk.application.concert.dto.response.ConcertSessionResponseDto;
import com.kokk.application.concert.dto.response.CustomConcertSessionSeatResponseDto;
import com.kokk.application.concert.usecase.GetAvailableConcertSessionUseCase;
import com.kokk.application.concert.usecase.GetConcertSessionSeatsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/concerts")
@RestController
public class ConcertController {

  private final GetAvailableConcertSessionUseCase getAvailableConcertSessionUseCase;
  private final GetConcertSessionSeatsUseCase getConcertSessionSeatsUseCase;
  @Operation(summary = "콘서트 세션 조회 - 예약 가능한 세션 조회")
  @GetMapping("/{concertId}/sessions")
  public ResponseEntity<List<ConcertSessionResponseDto>> getAvailableSessions(
          @PathVariable @NotNull Long concertId
  ) {
    List<ConcertSessionResponseDto> concertSessionResponseDtos = getAvailableConcertSessionUseCase.getAvailableConcertSessions(concertId);
    return ResponseEntity.ok(concertSessionResponseDtos);
  }

  @Operation(summary = "콘서트 좌석 조회")
  @GetMapping("/sessions/{concertSessionId}/seats")
  public ResponseEntity<List<CustomConcertSessionSeatResponseDto>> getConcertSeats(
          @PathVariable Long concertSessionId
  ) {
    List<CustomConcertSessionSeatResponseDto> result = getConcertSessionSeatsUseCase.getConcertSessionSeats(concertSessionId);
    return ResponseEntity.ok(result);
  }
}
