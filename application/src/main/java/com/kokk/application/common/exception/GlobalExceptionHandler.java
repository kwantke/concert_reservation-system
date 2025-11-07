package com.kokk.application.common.exception;


import com.kokk.domain.model.exception.CoreException;
import com.kokk.domain.model.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CoreException.class)
  public ResponseEntity<ErrorResponse> handleCustomException(CoreException ex) {
    log.error("Error occurs {}", ex.toString());
    ErrorResponse errorResponse = new ErrorResponse(
            ex.getErrorCode().getCode(),
            ex.getCustomMessage(),
            ex.getErrorCode().getHttpStatus().value()
    );

    return ResponseEntity.status(ex.getErrorCode().getHttpStatus().value()).body(errorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
    log.error("Error occurs {}", ex.toString());
    ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.name(), // 500
            ex.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR.value()
    );
    return ResponseEntity.status(500).body(errorResponse);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    log.error("Error occurs {}", ex.toString());
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error -> {
      errors.put(error.getField(), error.getDefaultMessage());
    });
    return errors;
  }



}
