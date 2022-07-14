package com.loyalty.jshan.global.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiResponseDto {
    
    private String returnCode; 
    private String returnMessage; 

    @Builder
    public ApiResponseDto(String returnCode, String returnMessage) { 
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }
}
