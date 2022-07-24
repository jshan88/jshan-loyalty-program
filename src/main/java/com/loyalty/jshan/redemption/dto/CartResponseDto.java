package com.loyalty.jshan.redemption.dto;

import com.loyalty.jshan.redemption.dto.item.flight.FlightItemResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CartResponseDto {

    private Long id;
    private LocalDateTime expiryDate;
    private int totalCount;
    private int totalMileage;
    private List<FlightItemResponseDto> flightItemList = new ArrayList<>();

    @Builder
    public CartResponseDto(Long id, LocalDateTime expiryDate,
                           int totalCount, int totalMileage, List<FlightItemResponseDto> flightItemList){
        this.id = id;
        this.expiryDate = expiryDate;
        this.totalCount = totalCount;
        this.totalMileage = totalMileage;
        this.flightItemList = flightItemList;
    }

}
