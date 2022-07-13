package com.loyalty.jshan.global.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;  

@Getter 
@NoArgsConstructor
public class ApiResponseWithError extends ApiResponse { 
 
    private String error; 
    private String errorType;
    private String message; 
  
    @Builder
    public ApiResponseWithError (int status, LocalDateTime timeStamp, String error, String errorType, String message) { 
        super(status, timeStamp); 
        this.error = error; 
        this.errorType = errorType; 
        this.message = message; 
    }    
}
