package com.kokk.application.concert.scheduler;

import com.kokk.application.concert.port.in.ConcertServicePort;
import com.kokk.application.concert.port.in.ReservationServicePort;
import com.kokk.domain.model.entity.Reservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConcertReservationScheduler {

  private final ReservationServicePort reservationServicePort;
  private final ConcertServicePort concertServicePort;

  @Scheduled(fixedDelayString = "60000") // 60초 마다 수행
  public void expireTemporaryReservations() {
    log.info("임시예약 만료 스케줄러 실행");
    final List<Reservation> concertReservations = reservationServicePort.getTemporaryReservationToBeExpired(5);
    if(concertReservations.size() != 0){
      concertReservations.forEach(concertReservation -> {
        try {
          reservationServicePort.cancelTemporaryReservation(concertReservation);
          concertServicePort.cancelTemporaryReservation(concertReservation.getReservedSeats());
        } catch (Exception e) {
          log.warn("임시예약 만료 중 오류 발생 (ID: {}): {}", concertReservation.getId(), e.getMessage());
        }
      });
    }
  }
}
