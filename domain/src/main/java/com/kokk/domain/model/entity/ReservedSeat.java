package com.kokk.domain.model.entity;

import com.kokk.domain.model.base.AuditingFields;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class ReservedSeat extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long reservationId;

  @Column(nullable = false)
  private Long concertSessionSeatId;

  public static ReservedSeat of(Long reservationId, Long concertSessionSeatId) {
    return ReservedSeat.builder()
            .reservationId(reservationId)
            .concertSessionSeatId(concertSessionSeatId)
            .build();

  }
}
