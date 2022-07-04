package com.loyalty.jshan.domain.order;

import com.loyalty.jshan.domain.CommonEntity;
import com.loyalty.jshan.domain.member.Member;
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