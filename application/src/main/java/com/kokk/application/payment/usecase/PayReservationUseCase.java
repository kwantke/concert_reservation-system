package com.kokk.application.payment.usecase;

import com.kokk.application.common.UseCase;
import com.kokk.application.concert.port.in.ReservationServicePort;
import com.kokk.application.payment.dto.request.PaymentRequestDto;
import com.kokk.application.payment.port.in.PaymentServicePort;
import com.kokk.application.queue.port.in.WaitingQueueServicePort;
import com.kokk.domain.exception.CoreException;
import com.kokk.domain.exception.concert.ConcertErrorCode;
import com.kokk.domain.model.entity.Reservation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class PayReservationUseCase {
  private final ReservationServicePort reservationServicePort;

  private final PaymentServicePort paymentServicePort;
  private final WaitingQueueServicePort waitingQueueServicePort;



  public void payReservation(String token, PaymentRequestDto request) {
    // 예약 확인
    Reservation reservation = reservationServicePort.getReservation(request.reservationId());
    if (!reservation.isTemporaryReservation()) {
      throw new CoreException(ConcertErrorCode.INVALID_RESERVATION_TEMPORARY_STATUS);
    }

    // 결제 생성
    paymentServicePort.createPayment(reservation.getId(), reservation.getUserId(), reservation.getTotalPrice());

    // 예약 상태 변경
    reservationServicePort.updateReservation(reservation);

    // 활성 대기열 만료
    waitingQueueServicePort.expireActiveQueue(token);
  }
}
