package com.kokk.application.queue.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kokk.domain.model.valueObject.WaitingQueue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


public record CreateWaitingQueueResponseDto(
        @Schema(name = "대기열 사용자 ID")
        Long userId,
        @Schema(name = "대기열 토큰")
        String token,
        @Schema(name = "생성 날짜")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt
) {


  public static CreateWaitingQueueResponseDto of(WaitingQueue waitingQueue) {
    return new CreateWaitingQueueResponseDto(
            waitingQueue.getUserId(),
            waitingQueue.getToken(),
            waitingQueue.getCreatedAt()
    );
  }
}
