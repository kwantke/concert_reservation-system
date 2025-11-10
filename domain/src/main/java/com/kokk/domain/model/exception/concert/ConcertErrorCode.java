package com.kokk.domain.model.exception.concert;

import com.kokk.domain.model.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ConcertErrorCode implements ErrorCode {
  // 예매 관련 에러 코드
  UNAVAILABLE_CONCERT_SESSION_SEAT("예약 가능한 좌석이 아닙니다.", HttpStatus.BAD_REQUEST),
  INVALID_OUTBOX_STATUS("아웃박스 상태는 필수 값입니다.", HttpStatus.BAD_REQUEST),
  INVALID_RESERVATION_STATUS("예약 상태는 필수 값입니다.", HttpStatus.BAD_REQUEST)
  ;
  private final String message;
  private final HttpStatus httpStatus;


  @Override
  public String getCode() {
    return this.name();
  }
}
