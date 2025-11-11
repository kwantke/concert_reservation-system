package com.kokk.domain.model.entity;

import com.kokk.domain.converter.OutboxStatusConverter;
import com.kokk.domain.converter.ReservationStatusConverter;
import com.kokk.domain.enums.ReservationStatus;
import com.kokk.domain.model.base.AuditingFields;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Reservation extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long concertSessionId;

  @Column(nullable = false)
  private Long userId;


  @Column(nullable = false)
  private Long totalPrice;

  @Convert(converter = ReservationStatusConverter.class)
  @Column(nullable = false)
  private ReservationStatus status;

  public static Reservation of(Long concertSessionId, Long userId, Long totalPrice, ReservationStatus status) {
    return Reservation.builder()
            .concertSessionId(concertSessionId)
            .userId(userId)
            .totalPrice(totalPrice)
            .status(status)
            .build();
  }

  public boolean isTemporaryReservation() {
    return this.status == ReservationStatus.TEMPORARY_RESERVED;

  }

  public void updateReservationStatus() {
    this.status = ReservationStatus.CONFIRMED;
  }
}
