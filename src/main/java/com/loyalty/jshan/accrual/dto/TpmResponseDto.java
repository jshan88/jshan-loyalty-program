package com.loyalty.jshan.accrual.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TpmResponseDto {

    private String depAPO;

    private String arrAPO;

    private int tpmValue;

    @Builder
    public TpmResponseDto(String depAPO, String arrAPO, int tpmValue) {
        this.depAPO = depAPO;
        this.arrAPO = arrAPO;
        this.tpmValue = tpmValue;
    }
}
