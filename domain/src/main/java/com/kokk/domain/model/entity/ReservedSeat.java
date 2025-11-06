package com.kokk.domain.model.entity;

import com.kokk.domain.model.base.AuditingFields;
import jakarta.persistence.*;
import lombok.Getter;

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


}
