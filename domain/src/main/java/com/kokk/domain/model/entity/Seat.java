package com.kokk.domain.model.entity;

import com.kokk.domain.model.base.AuditingFields;
import com.kokk.domain.model.valueObject.SeatNumber;
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
