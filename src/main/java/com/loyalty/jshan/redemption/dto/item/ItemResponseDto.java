package com.loyalty.jshan.redemption.dto.item;

import com.loyalty.jshan.redemption.dto.item.flight.FlightItemResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemResponseDto {

    private Long id;
    private String itemName;
    private int itemCount;
    private int mileage;
    private FlightItemResponseDto flightItemResponseDto;

    @Builder
    public ItemResponseDto(Long id, String itemName, int itemCount, int mileage, FlightItemResponseDto flightItemResponseDto) {
        this.id = id;
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.mileage = mileage;
        this.flightItemResponseDto = flightItemResponseDto;
    }
}
