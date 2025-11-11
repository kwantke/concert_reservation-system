package com.kokk.domain.model.entity;

import com.kokk.domain.model.base.AuditingFields;
import lombok.Getter;
import jakarta.persistence.*;
@Getter
@Entity
public class Venue extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;


}
