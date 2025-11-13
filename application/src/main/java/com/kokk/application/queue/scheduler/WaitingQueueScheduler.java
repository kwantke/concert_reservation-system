package com.kokk.application.queue.scheduler;

import com.kokk.application.queue.port.in.WaitingQueueServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WaitingQueueScheduler {
  private final WaitingQueueServicePort waitingQueueServicePort;

  @Scheduled(fixedDelay = 10000) // 10초 마다 수행
  public void activateWaitingQueue() {
    log.info("대기열 활성화 스케쥴러 실행");
    final int count = waitingQueueServicePort.batchActivate(3000);
    log.info("✅ 활성화 완료: {}명", count);
  }
}
