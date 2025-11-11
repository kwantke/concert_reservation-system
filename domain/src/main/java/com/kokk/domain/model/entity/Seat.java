package com.kokk.domain.model.entity;

import com.kokk.domain.model.base.AuditingFields;
import com.kokk.domain.model.valueObject.SeatNumber;
import lombok.Getter;
import jakarta.persistence.*;
@Getter
@Entity
public class Seat extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long venueId;

  @Column(nullable = false)
  private Character seatRow;

  @Column(nullable = false)
  private int seatColumn;

}
