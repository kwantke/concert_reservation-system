package com.kokk.application.concert.dto.request;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ReserveConcertRequest(
        @Schema(name = "콘서트 시즌 ID")
        @NotNull(message = "콘서트 시즌 ID는 필수 값 입니다.")
        Long concertSessionId,
        @Schema(name = "사용자 ID")
        @NotNull(message = "사용자 ID는 필수 값 입니다.")
        Long userId,
        @Schema(name = "죄석 리스트")
        @NotNull(message="좌석 ID는 필수 값 입니다.")
        List<Long> seatIds
        ) {

}
