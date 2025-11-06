package com.kokk.concert.infrastructure.web;

import com.kokk.concert.application.dto.request.CreateWaitingQueueRequest;
import io.swagger.v3.oas.annotations.Operation;
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


  @Operation(summary = "대기열 생성")
  @PostMapping
  public ResponseEntity<?> createWaitingQueue(
          @RequestBody CreateWaitingQueueRequest request
          ) {


    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
