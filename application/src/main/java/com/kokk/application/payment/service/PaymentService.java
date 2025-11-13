package com.kokk.application.payment.service;

import com.kokk.application.payment.port.out.PaymentRepositoryPort;
import com.kokk.application.payment.port.in.PaymentServicePort;
import com.kokk.domain.model.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService implements PaymentServicePort {
  private final PaymentRepositoryPort paymentRepositoryPort;
  @Override
  public Payment createPayment(Long reservationId, Long userId, Long totalPrice) {
    Payment payment = Payment.of(reservationId, userId, totalPrice);
    return paymentRepositoryPort.save(payment);
  }
}
