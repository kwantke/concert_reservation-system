package com.kokk.support.domain.model.entity;


import com.kokk.support.domain.model.base.AuditingFields;
import lombok.Getter;

@Getter
@Entity
public class Concert extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;
}
