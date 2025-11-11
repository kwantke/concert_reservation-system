package com.kokk.application.concert.usecase;

import com.kokk.application.common.UseCase;
import com.kokk.application.concert.dto.response.ConcertSessionResponseDto;
import com.kokk.application.concert.port.in.ConcertServicePort;
import com.kokk.domain.model.entity.ConcertSession;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class GetAvailableConcertSessionUseCase {

  private final ConcertServicePort concertServicePort;

  public List<ConcertSessionResponseDto> getAvailableConcertSessions(Long concertId) {
    return ConcertSessionResponseDto.from(concertServicePort.getAvailableConcertSessions(concertId));
  }
}
