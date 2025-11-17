package com.kokk.application.concert.usecase;

import com.kokk.application.common.UseCase;
import com.kokk.application.common.lock.DistributedLockExecutor;
import com.kokk.application.concert.dto.request.ReserveConcertRequest;
import com.kokk.application.concert.dto.response.ReserveConcertResponseDto;
import com.kokk.application.concert.port.in.ReservationServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
@Slf4j
@UseCase
public class ReserveConcertUseCase {

  private final ReservationServicePort reservationServicePort;

  private final DistributedLockExecutor distributedLockExecutor;
  private final TransactionTemplate transactionTemplate;

  private final String RESERVATION_LOCK_KEY = "seat_reservation:";
  public ReserveConcertResponseDto reserveConcert(ReserveConcertRequest request) {

    ReserveConcertResponseDto reserveConcertResponseDto = null;
    // 함수형 분산 락을 특정 메서드에 적용
    String lockKey = RESERVATION_LOCK_KEY + request.concertSessionId();
    long waitTime = 7_000; // 락 획득까지 대기할 시간
    long leaseTime = 5_000; // STW 발생 시 대기를 고려해 충분한 시간으로 설정

    try {
      reserveConcertResponseDto = distributedLockExecutor.executeWithLock(lockKey, waitTime, leaseTime,
              () -> transactionTemplate.execute(status -> {
                        return  reservationServicePort.reserveConcert(request);
                      }
              )
      );
    } catch (ObjectOptimisticLockingFailureException e) {
      log.warn("[낙관락 예외 발생] 좌석 예약 실패 - 사용자 {}, {}", request.userId(), e.getMessage());
    }

    return reserveConcertResponseDto;
  }
}
