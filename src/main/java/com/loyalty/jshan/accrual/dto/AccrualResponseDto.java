package com.loyalty.jshan.accrual.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccrualResponseDto {

    private Long memberId;
    private FlightAccrualResponseDto flightResponse;

    @Builder
    public AccrualResponseDto(Long memberId, FlightAccrualResponseDto flightResponse) {
        this.memberId = memberId;
        this.flightResponse = flightResponse;
    }
}
