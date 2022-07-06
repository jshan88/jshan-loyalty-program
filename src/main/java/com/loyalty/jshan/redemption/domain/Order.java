package com.loyalty.jshan.redemption.domain;

import com.loyalty.jshan.global.CommonEntity;
import com.loyalty.jshan.member.domain.Member;
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="memberId", referencedColumnName = "id")
    private Member member;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cartId", referencedColumnName = "id")
    private Cart cart;

    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatus orderStatus;

    @Column
    private int redeemedMiles;

    @Column
    private int refundMiles;

    @Builder
    public Order(String authNumber, Member member, Cart cart, OrderStatus orderStatus, int redeemedMiles, int refundMiles) {
        this.authNumber = authNumber;
        this.member = member;
        this.cart = cart;
        this.orderStatus = orderStatus;
        this.redeemedMiles = redeemedMiles;
        this.refundMiles = refundMiles;
    }
}
