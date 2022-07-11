package com.loyalty.jshan.redemption.dto;

import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.redemption.domain.Cart;
import com.loyalty.jshan.redemption.dto.item.flight.FlightItemRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class CartRequestDto {

    private Long memberId;

    private List<FlightItemRequestDto> flightItemList;

    @Builder
    public CartRequestDto(Long memberId, List<FlightItemRequestDto> flightItemList) {
        this.memberId = memberId;
        this.flightItemList = flightItemList;
    }

    public Cart toEntity(Member member) {

        return Cart.builder()
                .member(member)
                .itemList(new ArrayList<>())
                .build();
    }
}
