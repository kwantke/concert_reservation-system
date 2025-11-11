package com.kokk.infrastructure.payment.db;

import com.kokk.domain.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
