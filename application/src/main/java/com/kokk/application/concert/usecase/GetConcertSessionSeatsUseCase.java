package com.kokk.application.concert.usecase;

import com.kokk.application.common.UseCase;
import com.kokk.application.concert.dto.response.CustomConcertSessionSeatResponseDto;
import com.kokk.application.concert.port.in.ConcertServicePort;
import com.kokk.domain.model.valueObject.CustomConcertSessionSeat;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class GetConcertSessionSeatsUseCase {
  private final ConcertServicePort concertServicePort;
  public List<CustomConcertSessionSeatResponseDto> getConcertSessionSeats(Long concertSessionId) {
    List<CustomConcertSessionSeat> customConcertSessionSeats = concertServicePort.findConcertSessionSeatsByConcertSessionId(concertSessionId);
    return CustomConcertSessionSeatResponseDto.from(customConcertSessionSeats);
  }
}
