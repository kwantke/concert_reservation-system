package com.kokk.domain.model.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class ConcertSessionSeat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long seatId;

  @Column(nullable = false)
  private Long concertSessionId;

  @Column(nullable = false)
  private boolean reserved;

  @Version
  private int version;

}
