package com.kokk.domain.exception;

public record ErrorResponse(
        String errorCode,
        String message,
        int statusCode
) {
}
