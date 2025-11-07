package com.kokk.infrastructure.queue.web;

import com.kokk.application.queue.dto.request.CreateWaitingQueueRequestDto;
import com.kokk.application.queue.dto.response.CreateWaitingQueueResponseDto;
import com.kokk.application.queue.usecase.CreateWaitingQueueUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/waiting-queues")
@Tag(name = "WaitingQueue", description = "대기열 API")
public class WaitingQueueController {

  private final CreateWaitingQueueUseCase createWaitingQueueUseCase;


  @Operation(summary = "대기열 생성")
  @ApiResponse(responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateWaitingQueueResponseDto.class)))
  @PostMapping
  public ResponseEntity<CreateWaitingQueueResponseDto> createWaitingQueue(
          @RequestBody CreateWaitingQueueRequestDto request
          ) {
    final CreateWaitingQueueResponseDto createWaitingQueueResponseDto = createWaitingQueueUseCase.createWaitingQueue(request.userId());
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(createWaitingQueueResponseDto);
  }

}
