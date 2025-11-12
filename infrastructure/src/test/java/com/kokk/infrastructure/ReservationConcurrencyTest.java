package com.kokk.infrastructure;

import com.kokk.application.concert.dto.request.ReserveConcertRequest;
import com.kokk.application.concert.dto.response.ReserveConcertResponseDto;
import com.kokk.application.concert.port.out.ConcertSessionSeatRepositoryPort;
import com.kokk.application.concert.usecase.ReserveConcertUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("[통합 테스트] 예약 동시성 테스트")
@SpringBootTest
class ReservationConcurrencyTest {

    @Autowired
    private ReserveConcertUseCase reserveConcertUseCase;


    @Autowired
    private ConcertSessionSeatRepositoryPort concertSessionSeatRepositoryPort;

    private final Long concertSessionId = 1L;
    private final List<Long> seatIds = List.of(1L, 2L, 3L);


    @DisplayName("여러 스레드에서 동시에 동일한 좌석을 예약 시 중복 예약이 발생하지 않는다.")
    @Test
    void givenMultipleThreads_whenReservingSeatsConcurrently_thenShouldPreventDuplicateReservations() throws InterruptedException {
        // given: 100개의 동시 예약 요청 설정
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);
        List<ReserveConcertResponseDto> results = Collections.synchronizedList(new ArrayList<>());

        Runnable reservationTask = () -> {
            try {
                Long userId = Thread.currentThread().getId();
                ReserveConcertRequest request = new ReserveConcertRequest(concertSessionId, userId, seatIds);
                ReserveConcertResponseDto response = reserveConcertUseCase.reserveConcert(request);
                results.add(response); // 스레드 안전한 리스트에 추가
            } catch (Exception e) {
                System.err.println("❌ 예약 실패: " + e.getMessage());
            } finally {
                latch.countDown();
            }
        };

        // when: 모든 스레드가 동시에 좌석 예약 요청을 보냄
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(reservationTask);
        }

        latch.await(); // 모든 스레드가 완료될 때까지 대기
        executorService.shutdown();

        // then: 실제로 예약된 좌석 개수 확인
        long reservedSeatsCount = concertSessionSeatRepositoryPort.countReservedSeats(concertSessionId);
        System.out.println("✅ 최종 예약된 좌석 개수: " + reservedSeatsCount);

        assertThat(reservedSeatsCount)
                .as("동시성 이슈 발생으로 좌석이 중복 예약됨!")
                .isEqualTo(seatIds.size());
    }

}
