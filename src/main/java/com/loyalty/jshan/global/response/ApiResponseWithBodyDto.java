package com.loyalty.jshan.global.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiResponseWithBodyDto<T> extends ApiResponseDto {

    private T body;

    public ApiResponseWithBodyDto(String returnCode, String returnMessage, T body) {
        super(returnCode, returnMessage);
        this.body = body;
    }
    
}
