package com.loyalty.jshan.global.apiResponse;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor; 

@Getter 
@NoArgsConstructor 
public class ApiResponseWithSuccess<T> extends ApiResponse {

    private T data;

    @Builder
    public ApiResponseWithSuccess(int status, LocalDateTime timeStamp, T data) {
        super(status,timeStamp);
        this.data = data;
    }

    // changed the method to static, as it will be broadly used without instance variables inside.
    // public ApiResponseWithSucess<T> createApiResponse(T data) {  
    public static <T> ApiResponseWithSuccess<T> createApiResponse(T data) {
        
        return ApiResponseWithSuccess.<T>builder()
                            .status(HttpStatus.OK.value())
                            .timeStamp(LocalDateTime.now())
                            .data(data)
                            .build();
        // return new ApiResponseWithSuccess<T>(HttpStatus.OK.value(), LocalDateTime.now(), data);
    }
    
}
