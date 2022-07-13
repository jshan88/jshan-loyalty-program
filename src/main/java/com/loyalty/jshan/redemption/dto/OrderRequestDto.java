package com.loyalty.jshan.redemption.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderRequestDto {

    private Long memberId;
    private String requestType; // Issue, Reissue, Refund, Void
    private int redeemMileage;
    private int refundMileage;
    private CartRequestDto cart; 

    @Builder
    public OrderRequestDto (Long memberId, String requestType, int redeemMileage, int refundMileage, CartRequestDto cart) {
        this.memberId = memberId;
        this.requestType = requestType;
        this.redeemMileage = redeemMileage;
        this.refundMileage = refundMileage;
        this.cart = cart;
    }
}
