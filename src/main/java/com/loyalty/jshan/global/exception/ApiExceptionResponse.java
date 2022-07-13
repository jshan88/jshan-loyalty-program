package com.loyalty.jshan.global.exception;

import lombok.Builder;
import lombok.Getter;
// import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ApiExceptionResponse {
    private final int status;
    private final String error; 
    private final String errorType;
    private final String message;
//    private final Throwable throwable;
    // private final HttpStatus httpStatus;
    private final LocalDateTime timeStamp;

    @Builder
    public ApiExceptionResponse(int status, String error, String errorType, String message, LocalDateTime timeStamp) {
        this.status = status;
        this.error = error; 
        this.errorType = errorType; 
        this.message = message;
        this.timeStamp = timeStamp;
    }

}
