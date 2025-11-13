package com.kokk.infrastructure.payment.adapter;

import com.kokk.application.payment.port.out.PaymentRepositoryPort;
import com.kokk.domain.model.entity.Payment;
import com.kokk.infrastructure.payment.db.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {

  private final PaymentRepository paymentRepository;

  @Override
  public Payment save(Payment payment) {
    return paymentRepository.save(payment);
  }

}
