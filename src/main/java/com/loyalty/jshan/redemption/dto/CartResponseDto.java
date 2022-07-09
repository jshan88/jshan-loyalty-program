package com.loyalty.jshan.redemption.dto;

import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.redemption.domain.item.Item;
import com.loyalty.jshan.redemption.dto.item.ItemResponseDto;
import com.loyalty.jshan.redemption.dto.item.flight.FlightItemResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CartResponseDto {

    private LocalDateTime expiryDate;
    private int totalCount;
    private int totalMileage;
    private List<FlightItemResponseDto> flightItemList = new ArrayList<>();

    @Builder
    public CartResponseDto(LocalDateTime expiryDate,
                           int totalCount, int totalMileage, List<FlightItemResponseDto> flightItemList){

        this.expiryDate = expiryDate;
        this.totalCount = totalCount;
        this.totalMileage = totalMileage;
        this.flightItemList = flightItemList;
    }

}
