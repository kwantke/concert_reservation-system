package com.kokk.infrastructure.concert.web;

import com.kokk.application.concert.dto.request.ReserveConcertRequest;
import com.kokk.application.concert.dto.response.ConcertSessionResponseDto;
import com.kokk.application.concert.dto.response.CustomConcertSessionSeatResponseDto;
import com.kokk.application.concert.dto.response.ReserveConcertResponseDto;
import com.kokk.application.concert.usecase.GetAvailableConcertSessionUseCase;
import com.kokk.application.concert.usecase.GetConcertSessionSeatsUseCase;
import com.kokk.application.concert.usecase.ReserveConcertUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/concerts")
@RestController
@Tag(name = "Concert", description = "콘서트 API")
public class ConcertController {

  private final GetAvailableConcertSessionUseCase getAvailableConcertSessionUseCase;
  private final GetConcertSessionSeatsUseCase getConcertSessionSeatsUseCase;
  private final ReserveConcertUseCase reserveConcertUseCase;
  @Operation(summary = "콘서트 세션 조회 - 예약 가능한 세션 조회")
  @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
          array = @ArraySchema(schema = @Schema(implementation = ConcertSessionResponseDto.class))))
  @GetMapping("/{concertId}/sessions")
  public ResponseEntity<List<ConcertSessionResponseDto>> getAvailableSessions(
          @PathVariable @NotNull Long concertId
  ) {
    List<ConcertSessionResponseDto> result = getAvailableConcertSessionUseCase.getAvailableConcertSessions(concertId);
    return ResponseEntity.ok(result);
  }

  @Operation(summary = "콘서트 좌석 조회")
  @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
          array = @ArraySchema(schema = @Schema(implementation = CustomConcertSessionSeatResponseDto.class))))
  @GetMapping("/sessions/{concertSessionId}/seats")
  public ResponseEntity<List<CustomConcertSessionSeatResponseDto>> getConcertSeats(
          @PathVariable Long concertSessionId
  ) {
    List<CustomConcertSessionSeatResponseDto> result = getConcertSessionSeatsUseCase.getConcertSessionSeats(concertSessionId);
    return ResponseEntity.ok(result);
  }

  @Operation(summary = "콘서트 예약")
  @ApiResponse(responseCode = "201", content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ReserveConcertResponseDto.class)))
  @PostMapping("/session/reservations")
  public ResponseEntity<ReserveConcertResponseDto> reserveConcert(
          @RequestBody ReserveConcertRequest request
          ) {
    ReserveConcertResponseDto result = reserveConcertUseCase.reserveConcert(request);
    return ResponseEntity.ok(result);

  }
}
