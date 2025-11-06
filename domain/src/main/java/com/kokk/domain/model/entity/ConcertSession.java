package com.kokk.domain.model.entity;

import com.kokk.domain.model.base.AuditingFields;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class ConcertSession extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Concert concert;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Venue venue;

  @Column(nullable = false)
  protected LocalDateTime start_date;
}
