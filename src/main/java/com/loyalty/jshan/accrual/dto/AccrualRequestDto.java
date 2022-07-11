package com.loyalty.jshan.accrual.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class AccrualRequestDto {

    private Long memberId;

    private FlightAccrualRequestDto flightRequest;

    @Builder
    public AccrualRequestDto(Long memberId, FlightAccrualRequestDto flightRequest) {
        this.memberId = memberId;
        this.flightRequest = flightRequest;
    }
}
