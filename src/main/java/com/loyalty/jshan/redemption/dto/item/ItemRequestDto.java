package com.loyalty.jshan.redemption.dto.item;

import com.loyalty.jshan.redemption.dto.item.flight.FlightItemRequestDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemRequestDto {
    
    private FlightItemRequestDto flightItemRequestDto;

    @Builder
    public ItemRequestDto(FlightItemRequestDto flightItemRequestDto) {

        this.flightItemRequestDto = flightItemRequestDto;
    }
}
