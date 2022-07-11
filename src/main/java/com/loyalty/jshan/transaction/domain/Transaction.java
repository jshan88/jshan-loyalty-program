package com.loyalty.jshan.transaction.domain;

import com.loyalty.jshan.global.CommonEntity;
import com.loyalty.jshan.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Transaction extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", referencedColumnName = "id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "TXN_TYPE", length = 10, nullable = false)
    private TransactionType txnType;

    @Enumerated(EnumType.STRING)
    private TransactionSubType txnSubType;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Enumerated(EnumType.STRING)
    private SourceType sourceType; // flight, creditcard, hotel, others

    private String sourceSubType; // KE,DL,AF,..(flight), USbank,Chase,...(creditcard), Marriott(hotel), ...

    private String bookingClass;

    private String depAPO;

    private String arrAPO;

    private String departureDate;

    private String flightNumber;

    private int mileage;

    @Builder
    public Transaction(Member member, TransactionType txnType, TransactionSubType txnSubType, TransactionStatus status,
                       SourceType sourceType, String sourceSubType, String bookingClass, String depAPO, String arrAPO,
                       String departureDate, String flightNumber, int mileage) {
        this.member = member;
        this.txnType = txnType;
        this.txnSubType = txnSubType;
        this.status = status;
        this.sourceType = sourceType;
        this.sourceSubType = sourceSubType;
        this.bookingClass = bookingClass;
        this.depAPO = depAPO;
        this.arrAPO = arrAPO;
        this.departureDate = departureDate;
        this.flightNumber = flightNumber;
        this.mileage = mileage;
    }
}
