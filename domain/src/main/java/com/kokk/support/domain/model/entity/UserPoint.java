package com.kokk.support.domain.model.entity;

import com.kokk.support.domain.model.base.AuditingFields;
import lombok.Getter;

@Getter
@Entity
public class UserPoint extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long userId;

  @Column(nullable = false)
  private int amount;

  @Version
  private int version;
}
