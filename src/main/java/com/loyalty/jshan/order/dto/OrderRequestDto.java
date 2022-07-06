package com.loyalty.jshan.order.dto;

import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.order.domain.OrderStatus;

import javax.persistence.*;

public class OrderRequestDto {

    private Long memberId;

    private OrderStatus orderStatus;

    private int redeemMileage;

}
