package com.kokk.application.payment.dto.request;

import jakarta.validation.constraints.NotNull;

public record PaymentRequestDto(
        @NotNull
        Long userId,
        @NotNull
        Long reservationId
) {
}
