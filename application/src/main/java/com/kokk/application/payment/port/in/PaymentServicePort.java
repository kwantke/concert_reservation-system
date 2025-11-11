package com.kokk.application.payment.port.in;

import com.kokk.domain.model.entity.Payment;

public interface PaymentServicePort {
  Payment createPayment(Long id, Long userId, Long totalPrice);
}
