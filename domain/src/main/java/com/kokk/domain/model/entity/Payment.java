package com.kokk.domain.model.entity;

import com.kokk.domain.model.base.AuditingFields;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Payment extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(nullable = false)
  private Reservation reservation;

  @ManyToOne
  @JoinColumn(nullable = false)
  private User user;

  @Column(nullable = false)
  private int amount;
}
