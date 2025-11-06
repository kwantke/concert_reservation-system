package com.kokk.domain.model.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class ConcertSessionSeat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Seat seat;

  @ManyToOne
  @JoinColumn(nullable = false)
  private ConcertSession concertSession;

  private boolean reserved;

  @Version
  private int version;

}
