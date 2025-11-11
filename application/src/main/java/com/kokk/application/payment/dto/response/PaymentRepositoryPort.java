package com.kokk.application.payment.dto.response;

import com.kokk.domain.model.entity.Payment;

public interface PaymentRepositoryPort {
  Payment save(Payment payment);
}
