package com.kokk.application.concert.service;

import com.kokk.application.concert.dto.request.ReserveConcertRequest;
import com.kokk.application.concert.port.in.ReservationServicePort;
import com.kokk.application.concert.port.out.ConcertSessionSeatRepositoryPort;
import com.kokk.application.concert.port.out.ReservationRepositoryPort;
import com.kokk.application.concert.port.out.ReservedSeatRepositoryPort;
import com.kokk.domain.model.entity.ConcertSessionSeat;
import com.kokk.domain.model.entity.Reservation;
import com.kokk.domain.model.entity.ReservedSeat;
import com.kokk.domain.model.enums.ReservationStatus;
import com.kokk.domain.validation.ReservationValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService implements ReservationServicePort {

  private final ConcertSessionSeatRepositoryPort concertSessionSeatRepositoryPort;
  private final ReservationRepositoryPort reservationRepositoryPort;
  private final ReservedSeatRepositoryPort reservedSeatRepositoryPort;

  private final ReservationValidation reservationValidation;

  @Override
  public void reserveConcert(ReserveConcertRequest request) {
    Long concertSessionId = request.concertSessionId();
    Long userId = request.userId();

    List<ConcertSessionSeat> concertSessionSeats = validateReservationConstraints(concertSessionId, userId, request.seatIds());

    reserveConcertSeat(concertSessionId, userId, concertSessionSeats);


  }

  private void reserveConcertSeat(Long concertSessionId, Long userId, List<ConcertSessionSeat> concertSessionSeats) {
    // 예약 정보 저장후 ID 반환
    Long reservationId = saveReservation(concertSessionId, userId, concertSessionSeats);

    // 예약 좌석 정보 저장
    saveReservedSeat(reservationId, concertSessionSeats);

    // 콘서트 시즌 좌석 예약된 상태로 업데이트
    updateConcertSessionSeat(concertSessionSeats);
  }

  /**좌석 예약 여부 확인 */

  private List<ConcertSessionSeat> validateReservationConstraints(Long concertSessionId, Long userId, List<Long> seatIds) {
    // 요청 죄석 예약 여부 확인
    List<ConcertSessionSeat> concertSessionSeats = concertSessionSeatRepositoryPort.findByConcertSessionIdAndSeatIdInAndReservedFalse(concertSessionId, seatIds);

    reservationValidation.validateSeatsExist(seatIds, concertSessionSeats);

    return concertSessionSeats;
  }

  /** 예약 정보 저장*/
  private Long saveReservation(Long concertSessionId,Long userId, List<ConcertSessionSeat> concertSessionSeats) {
    Long totalPrice = concertSessionSeats.stream()
            .mapToLong(ConcertSessionSeat::getPrice)
            .sum();

    Reservation reservation = Reservation.of(concertSessionId, userId, totalPrice, ReservationStatus.TEMPORARY_RESERVED.getStatusCode());

    Long reservationId = reservationRepositoryPort.save(reservation)
            .getId();
    return reservationId;
  }

  /** 예약 죄석 정보 저장*/
  private void saveReservedSeat(Long reservationId, List<ConcertSessionSeat> concertSessionSeats) {
    for (ConcertSessionSeat css : concertSessionSeats) {
      ReservedSeat reservedSeat = ReservedSeat.of(reservationId, css.getConcertSessionId());
      reservedSeatRepositoryPort.save(reservedSeat);
    }
  }

  private void updateConcertSessionSeat(List<ConcertSessionSeat> concertSessionSeats) {
    concertSessionSeats.forEach(ConcertSessionSeat::reserve);
    concertSessionSeatRepositoryPort.saveAll(concertSessionSeats);

  }
}
