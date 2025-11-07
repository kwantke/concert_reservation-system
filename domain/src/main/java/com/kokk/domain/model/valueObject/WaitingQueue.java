package com.kokk.domain.model.valueObject;

import com.kokk.domain.model.enums.QueueStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WaitingQueue {
  private Long id;
  private Long userId;
  private String token;
  private Long waitingOrder;
  private QueueStatus status;
  private LocalDateTime activatedAt;
  private LocalDateTime expiredAt;
  private LocalDateTime lastActionedAt;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;


  /**
   * 대기열 새로 생성 시 사용
   * */
  public WaitingQueue(final Long userId, final String token, final LocalDateTime createdAt) {
    this.userId = userId;
    this.token = token;
    this.createdAt = createdAt;
    this.status = QueueStatus.WAITING;
  }

  public static WaitingQueue of(final Long userId, final String token, final LocalDateTime createdAt) {
    return new WaitingQueue(userId, token, createdAt);
  }

}
