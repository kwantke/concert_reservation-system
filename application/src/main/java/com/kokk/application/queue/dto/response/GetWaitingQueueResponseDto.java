package com.kokk.application.queue.dto.response;

import com.kokk.domain.model.valueObject.WaitingQueue;
import io.swagger.v3.oas.annotations.media.Schema;

public record GetWaitingQueueResponseDto(
        @Schema(name = "대기열 사용자ID")
        Long userId,
        @Schema(name = "대기열 상태")
        String status,

        @Schema(name = "대기 번호")
        Long waitingNum
) {

        public static GetWaitingQueueResponseDto of(final WaitingQueue waitingQueue) {
                return new GetWaitingQueueResponseDto(
                        waitingQueue.getUserId(),
                        waitingQueue.getStatus().getValue(),
                        waitingQueue.getWaitingRank()
                );
        }
}
