package com.kokk.domain.model.exception.queue;

import com.kokk.domain.model.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum WaitingQueueErrorCode implements ErrorCode {
  INVALID_WAITING_QUEUE("해당 대기열 정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
  INVALID_ACTIVATED_QUEUE("해당 토큰은 활성화된 상태가 아닙니다.", HttpStatus.BAD_REQUEST),
  ;

  private final String message;
  private final HttpStatus httpStatus;

  @Override
  public String getCode() {
    return this.name();
  }
}
