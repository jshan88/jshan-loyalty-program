package com.loyalty.jshan.global.apiResponse;

import java.time.LocalDateTime;

import com.loyalty.jshan.global.exception.ApiErrorCode;

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

    public static ApiResponseWithError createApiResponse(ApiErrorCode errorCode) { 

        return ApiResponseWithError.builder() 
                                    .status(errorCode.getHttpStatus().value())
                                    .error(errorCode.getHttpStatus().name())
                                    .errorType(errorCode.name())
                                    .message(errorCode.getDescription())                
                                    .timeStamp(LocalDateTime.now())
                                    .build(); 
    }
}
