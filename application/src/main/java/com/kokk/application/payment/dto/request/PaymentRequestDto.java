package com.kokk.application.payment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record PaymentRequestDto(
        @Schema(name = "사용자 ID")
        @NotNull(message = "사용자 ID는 필수 값입니다.")
        Long userId,
        @Schema(name = "예약 ID")
        @NotNull(message = "예약 ID는 필수 값입니다.")
        Long reservationId
) {
}
