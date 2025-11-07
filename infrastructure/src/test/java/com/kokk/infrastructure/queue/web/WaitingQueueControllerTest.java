package com.kokk.infrastructure.queue.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kokk.application.queue.dto.request.CreateWaitingQueueRequestDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[통합테스트] 대기열 토근 컨트롤러")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class WaitingQueueControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @DisplayName("사용자ID로 대기열 토근 발급하여 토큰 정보와 201 응답을 반환한다.")
  @Test
  void givenUserId_whenCreateToken_thenReturn201() throws Exception {
    //Given
    CreateWaitingQueueRequestDto dto = new CreateWaitingQueueRequestDto(1L);

    //When & Then
    mockMvc.perform(post("/api/v1/waiting-queues")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.userId").exists())
            .andExpect(jsonPath("$.token").exists())
            .andExpect(jsonPath("$.createdAt").exists());
  }

  @DisplayName("정상적인 대기열 토큰으로 대기열 정보를 조회합니다.")
  @Test
  void givenValidToken_whenGetWaitingQueue_thenReturn200() throws Exception {
    //Given
    String token = "3cf72a1e-308c-448c-be07-52b13f7addaf";

    //When & Then
    mockMvc.perform(get("/api/v1/waiting-queues")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("QUEUE-TOKEN", token))
            .andExpect(jsonPath("$.userId").exists())
            .andExpect(jsonPath("$.status").exists())
            .andExpect(jsonPath("$.waitingNum").exists());
  }

  @DisplayName("잘못된 대기열 토큰으로 대기열 정보를 조회시 400 응답을 반환합니다.")
  @Test
  void givenInvalidToken_whenGetWaitingQueue_thenReturn400() throws Exception {
    //Given
    String token = "3cf72a1e-308c-448c-be07-52b13f7addaf";

    //When & Then
    mockMvc.perform(get("/api/v1/waiting-queues")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("QUEUE-TOKEN", token))
            .andExpect(status().isBadRequest());
  }
}