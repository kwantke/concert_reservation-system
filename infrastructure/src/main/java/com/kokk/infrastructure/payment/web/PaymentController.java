package com.kokk.infrastructure.payment.web;

import com.kokk.application.payment.dto.request.PaymentRequestDto;
import com.kokk.application.payment.usecase.PayReservationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Tag(name = "Payment", description = "결제 API")

public class PaymentController {

  private final PayReservationUseCase payReservationUseCase;
  @Operation(summary = "예약 결제")
  @PostMapping
  public ResponseEntity payReservation(
          @RequestHeader("QUEUE-TOKEN") String token,
          @RequestBody PaymentRequestDto request
  ) {
    payReservationUseCase.payReservation(token, request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
