package com.loyalty.jshan.global.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor; 

@Getter 
@NoArgsConstructor 
public class ApiResponseWithSucess<T> extends ApiResponse {

    private T data;

    // @Builder
    public ApiResponseWithSucess(int status, LocalDateTime timeStamp, T data) {
        super(status,timeStamp);
        this.data = data;
    }

    // public static <T> ApiResponseWithSucess<T> createApiResponse(T data) { 
    public static <T> ApiResponseWithSucess<T> createApiResponse(T data) { 
        return new ApiResponseWithSucess<T>(HttpStatus.OK.value(), LocalDateTime.now(), data);
    }
    
}
