package com.kokk.support.domain.model.entity;

import com.kokk.support.domain.model.base.AuditingFields;
import lombok.Getter;

@Getter
@Entity
public class User extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  private String name;

  @Column(nullable = false, length = 100, unique = true)
  private String email;

}
