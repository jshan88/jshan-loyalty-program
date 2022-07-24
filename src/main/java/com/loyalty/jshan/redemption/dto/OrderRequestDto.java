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
    private Long cartId;
//    private String requestType; // Issue, Reissue, Refund, Void
//    private int redeemMileage;
//    private int refundMileage;

    @Builder
    public OrderRequestDto (Long memberId, Long cartId  ) {
        this.memberId = memberId;
        this.cartId = cartId;
//        this.requestType = requestType;
//        this.redeemMileage = redeemMileage;
//        this.refundMileage = refundMileage;
    }
}
