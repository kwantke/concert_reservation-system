package com.kokk.support.domain.model.entity;

import com.kokk.support.domain.model.base.AuditingFields;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class ConcertSession extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long concertId;

  @Column(nullable = false)
  private Long venueId;

  @Column(nullable = false)
  protected LocalDateTime start_date;
}
