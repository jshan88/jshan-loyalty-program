package com.loyalty.jshan.redemption.dto;

import com.loyalty.jshan.redemption.domain.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderResponseDto {

    private Long id;
    private String authNumber;
    private Long memberId;
    private OrderStatus orderStatus;
    private String activeFlg;
    private int redeemedMiles;
    private int refundMiles;

    @Builder
    public OrderResponseDto(Long id, String authNumber, Long memberId, OrderStatus orderStatus, String activeFlg, int redeemedMiles, int refundMiles) {
        this.id = id;
        this.authNumber = authNumber;
        this.memberId = memberId;
        this.orderStatus = orderStatus;
        this.activeFlg = activeFlg;
        this.redeemedMiles = redeemedMiles;
        this.refundMiles = refundMiles;
    }

}
