package com.kokk.infrastructure.concert.adapter;

import com.kokk.application.concert.port.out.ConcertSessionRepositoryPort;
import com.kokk.domain.model.entity.ConcertSession;
import com.kokk.domain.model.provider.time.TimeProvider;
import com.kokk.infrastructure.concert.db.ConcertSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ConcertSessionRepositoryAdapter implements ConcertSessionRepositoryPort {

  private final ConcertSessionRepository concertSessionRepository;

  private final TimeProvider timeProvider;
  @Override
  public List<ConcertSession> findByConcertId(Long concertId) {
    // 콘서트 세션과 현재 시간 이후의 일정 조회
    return concertSessionRepository.findByConcertIdAndStartDateAfterOrderByStartDateAsc(concertId, timeProvider.now());
  }
}
