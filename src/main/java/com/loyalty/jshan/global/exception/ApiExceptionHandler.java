package com.loyalty.jshan.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException (ApiRequestException e) {
        // 1. Create payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = ApiException.builder()
                .message(e.getMessage())
                .httpStatus(badRequest)
                .timestamp(LocalDateTime.now())
                .build();

        // 2. Return response entity.
        return new ResponseEntity<>(apiException, badRequest);
    }
}
