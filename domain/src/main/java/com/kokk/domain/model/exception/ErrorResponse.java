package com.kokk.domain.model.exception;

public record ErrorResponse(
        String errorCode,
        String message,
        int statusCode
) {
}
