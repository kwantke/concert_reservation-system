package com.kokk.domain.model.entity;

import com.kokk.concert.domain.model.valueObject.SeatNumber;
import com.kokk.domain.model.base.AuditingFields;
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
