package com.kokk.domain.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ReservationStatus {
  TEMPORARY_RESERVED("임시 예약",1),
  CONFIRMED("확정 예약",2);

  private final String statusName;
  private final int statusCode;


}
