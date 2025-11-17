package com.kokk.application.queue.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record CreateWaitingQueueRequestDto(
        @Schema(name = "사용자 ID")
        @NotNull(message = "사용자 ID는 필수 값입니다.")
        Long userId
)
{}
