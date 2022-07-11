package com.loyalty.jshan.accrual.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FlightAccrualRequestDto {

    private String carrier;
    private String bookingClass;
    private String depAPO;
    private String arrAPO;
    private String depDate;
    private String flightNumber;

    @Builder
    public FlightAccrualRequestDto(String carrier, String bookingClass, String depAPO, String arrAPO, String depDate, String flightNumber) {
        this.carrier = carrier;
        this.bookingClass = bookingClass;
        this.depAPO = depAPO;
        this.arrAPO = arrAPO;
        this.depDate = depDate;
        this.flightNumber = flightNumber;
    }
}
