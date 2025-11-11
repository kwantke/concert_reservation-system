package com.kokk.domain.model.entity;

import com.kokk.domain.exception.CoreException;
import com.kokk.domain.exception.concert.ConcertErrorCode;
import jakarta.persistence.Entity;
import lombok.Getter;
import jakarta.persistence.*;
@Getter
@Entity
public class ConcertSessionSeat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY) // 연관관계 설정
  @JoinColumn(name = "seat_id", nullable = false)
  private Seat seat;

  @Column(nullable = false)
  private Long concertSessionId;

  @Column(nullable = false)
  private Long price;

  @Column(nullable = false)
  private boolean reserved;

  @Version
  private int version;

  public void reserve() {
    if(reserved) {
      throw new CoreException(ConcertErrorCode.UNAVAILABLE_CONCERT_SESSION_SEAT);
    }
    this.reserved = true;
  }
}
