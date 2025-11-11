package com.kokk.infrastructure.concert.db;

import com.kokk.domain.model.entity.ConcertSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConcertSessionRepository extends JpaRepository<ConcertSession, Long> {
  List<ConcertSession> findByConcertIdAndStartDateAfterOrderByStartDateAsc(Long concertId, LocalDateTime now);
}
