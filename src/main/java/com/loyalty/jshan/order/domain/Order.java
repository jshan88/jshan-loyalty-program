package com.loyalty.jshan.order.domain;

import com.loyalty.jshan.global.CommonEntity;
import com.loyalty.jshan.member.domain.Member;
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

    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatus orderStatus;

    @Column
    private int redeemedMiles;

    @Column
    private int refundMiles;
}
