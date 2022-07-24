package com.loyalty.jshan.redemption.domain;

import com.loyalty.jshan.global.CommonEntity;
import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.redemption.domain.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="Orders")
public class Order extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String authNumber;

    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatus orderStatus;

    @Column(length = 1)
    private String activeFlg;

    @Column
    private int redeemedMiles;

    @Column
    private int refundMiles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId", referencedColumnName = "id")
    private Member member;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cartId", referencedColumnName = "id")
    private Cart cart;

    @Builder
    public Order(String authNumber, Member member, Cart cart, OrderStatus orderStatus, String activeFlg, int redeemedMiles, int refundMiles) {
        this.authNumber = authNumber;
        this.member = member;
        this.cart = cart;
        this.orderStatus = orderStatus;
        this.activeFlg = activeFlg;
        this.redeemedMiles = redeemedMiles;
        this.refundMiles = refundMiles;
    }

    public Order cancelOrder() {

        this.activeFlg = "N";
        return generateRefundOrder();
    }

    private Order generateRefundOrder() {

        return Order.builder()
                .authNumber(this.authNumber)
                .member(this.member)
                .orderStatus(this.orderStatus)
                .activeFlg("Y")
                .redeemedMiles(0)
                .refundMiles(this.redeemedMiles)
                .build();
    }
}
