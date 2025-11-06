package com.kokk.domain.model.entity;

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

  @ManyToOne
  @JoinColumn(nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(nullable = false)
  private ConcertSession concertSession;

  @Column(nullable = false)
  private int status;

  @OneToMany(mappedBy = "reservation")
  private List<ReservedSeat> reservedSeats;
}
