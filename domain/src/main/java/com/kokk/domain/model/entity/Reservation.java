package com.kokk.domain.model.entity;

import com.kokk.domain.model.base.AuditingFields;
import com.kokk.domain.model.enums.ReservationStatus;
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

  @Column(nullable = false)
  private int status;

  public static Reservation of(Long concertSessionId, Long userId, Long totalPrice, int status) {
    return Reservation.builder()
            .concertSessionId(concertSessionId)
            .userId(userId)
            .totalPrice(totalPrice)
            .status(status)
            .build();
  }

}
