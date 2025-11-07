package com.kokk.domain.model.exception.queue;

import com.kokk.domain.model.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum WaitingQueueErrorCode implements ErrorCode {
  WAITING_QUEUE_NOT_FOUND("해당 대기열 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  ;

  private final String message;
  private final HttpStatus httpStatus;

  @Override
  public String getCode() {
    return this.name();
  }
}
