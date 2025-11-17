package com.kokk.application.payment.port.out;

import com.kokk.domain.model.entity.Payment;

public interface PaymentRepositoryPort {
  Payment save(Payment payment);
}
