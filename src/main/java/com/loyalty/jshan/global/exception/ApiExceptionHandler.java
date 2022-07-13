package com.loyalty.jshan.global.exception;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
 
import com.loyalty.jshan.global.response.ApiResponseWithError;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException (ApiRequestException e) {
        // 1. Create payload containing exception details
        // HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiResponseWithError apiResponse = ApiResponseWithError.builder() 
                .status(e.getApiErrorCode().getHttpStatus().value())
                .error(e.getApiErrorCode().getHttpStatus().name())
                .errorType(e.getApiErrorCode().name())
                .message(e.getApiErrorCode().getDescription())                
                .timeStamp(LocalDateTime.now())
                .build();

        // 2. Return response entity.
        return new ResponseEntity<>(apiResponse, e.getApiErrorCode().getHttpStatus());
    }
}
