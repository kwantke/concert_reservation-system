package com.kokk.application.queue.usecase;

import com.kokk.application.queue.dto.response.CreateWaitingQueueResponseDto;
import com.kokk.application.queue.port.in.WaitingQueueServicePort;
import com.kokk.domain.model.valueObject.WaitingQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

//AFTER_EACH_TEST_METHOD : 테스트 메서드 실행 후 스프링 컨텍스트를 초기화하여
//테스트 간 상태 공유가 발생하지 않도록 하기 위함
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class) // Mockito 기능(@Mock, @InjectMocks 등)을 자동으로 활성화
@DisplayName("대기열 생성 유스케이스 테스트")
class CreateWaitingQueueUseCaseTest {


  @Mock
  private WaitingQueueServicePort waitingQueueServicePort;

  @InjectMocks
  private CreateWaitingQueueUseCase sut;

  private WaitingQueue mockWaitingQueue;

  @BeforeEach
  void setUp() {
    mockWaitingQueue = WaitingQueue.of(
            1L,
            "token",
            LocalDateTime.of(2025, 11, 7, 11, 11, 11)
    );
  }
  @Test
  void givenUserId_whenCreateToken_thenReturnQueueInfo() {
    //Given
    Long userId = 1L;
    when(waitingQueueServicePort.createWaitingQueue(userId)).thenReturn(mockWaitingQueue);

    //When
    CreateWaitingQueueResponseDto result = sut.createWaitingQueue(userId);

    //Then
    assertNotNull(result);
    verify(waitingQueueServicePort, times(1)).createWaitingQueue(userId);

  }

}