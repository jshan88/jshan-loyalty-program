package com.loyalty.jshan.accrual.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccrualRateResponseDto {

    private String carrier;

    private String bookingClass;

    private int accrualRate;

    @Builder
    public AccrualRateResponseDto(String carrier, String bookingClass, int accrualRate) {
        this.carrier = carrier;
        this.bookingClass = bookingClass;
        this.accrualRate = accrualRate;
    }
}
