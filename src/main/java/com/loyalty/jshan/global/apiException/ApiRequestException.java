package com.loyalty.jshan.global.apiException;

import lombok.Getter; 

@Getter 
public class ApiRequestException extends RuntimeException {

    private final ApiErrorCode apiErrorCode;

    public ApiRequestException(ApiErrorCode apiErrorCode) { 
        this.apiErrorCode = apiErrorCode;
    }

    // public ApiRequestException(String message) {
    //     super(message);
    // }

    // public ApiRequestException(String message, Throwable cause) {
    //     super(message, cause);
    // }
}
