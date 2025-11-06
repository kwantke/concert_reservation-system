package com.kokk.concert.domain.model.entity;

import com.kokk.domain.model.base.AuditingFields;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Reservation extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long userId;

  @Column(nullable = false)
  private Long concertSessionId;

  @Column(nullable = false)
  private int status;

}
