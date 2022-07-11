package com.loyalty.jshan.global.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ApiException {
    private final String message;
//    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final LocalDateTime timestamp;

    @Builder
    public ApiException(String message, HttpStatus httpStatus, LocalDateTime timestamp) {
        this.message = message;
//        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }
}
