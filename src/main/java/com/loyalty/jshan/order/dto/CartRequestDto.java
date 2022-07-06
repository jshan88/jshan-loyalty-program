package com.loyalty.jshan.order.dto;

import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.order.domain.Cart;
import com.loyalty.jshan.order.dto.item.flight.FlightItemRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class CartRequestDto {

    private Long memberId;

    private List<FlightItemRequestDto> FlightItemList;

    @Builder
    public CartRequestDto(Long memberId, List<FlightItemRequestDto> FlightItemList) {
        this.memberId = memberId;
        this.FlightItemList = FlightItemList;
    }

    public Cart toEntity(Member member) {

        return Cart.builder().member(member).build();
    }
}
