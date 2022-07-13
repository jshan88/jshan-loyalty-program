package com.loyalty.jshan.global.exception;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
 
import com.loyalty.jshan.global.apiResponse.ApiResponseWithError;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException (ApiRequestException e) {
    // public ApiResponseWithError handleApiRequestException (ApiRequestException e) {
        // 1. Create payload containing exception details
        // HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        // ApiResponseWithError apiResponse = ApiResponseWithError.createApiResponse(e.getApiErrorCode());

        // 2. Return response entity.
        return new ResponseEntity<>(ApiResponseWithError.createApiResponse(e.getApiErrorCode()), e.getApiErrorCode().getHttpStatus()); 
    }
}
