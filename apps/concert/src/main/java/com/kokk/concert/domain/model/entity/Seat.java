package com.kokk.concert.domain.model.entity;

import com.kokk.concert.domain.model.valueObject.SeatNumber;
import com.kokk.support.domain.model.base.AuditingFields;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Seat extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Embedded
  private SeatNumber seatNumber;

  @Column(nullable = false)
  private Long venueId;

}
