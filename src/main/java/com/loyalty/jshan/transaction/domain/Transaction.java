package com.loyalty.jshan.transaction.domain;

import com.loyalty.jshan.global.CommonEntity;
import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.transaction.domain.enums.SourceType;
import com.loyalty.jshan.transaction.domain.enums.TransactionStatus;
import com.loyalty.jshan.transaction.domain.enums.TransactionSubType;
import com.loyalty.jshan.transaction.domain.enums.TransactionType;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cancelledTxnId", referencedColumnName = "id")
    private Transaction cancelledTransaction;

    @Enumerated(EnumType.STRING)
    @Column(name = "TXN_TYPE", length = 10, nullable = false)
    private TransactionType txnType; // ACCRUAL, REDEMPTION

    @Enumerated(EnumType.STRING)
    private TransactionSubType txnSubType; // PRODUCT, CANCELLATION

    @Enumerated(EnumType.STRING)
    private TransactionStatus status; // PROCESSED, CANCELLED

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
    public Transaction(Transaction cancelledTransaction, Member member, TransactionType txnType, TransactionSubType txnSubType,
                       TransactionStatus status, SourceType sourceType, String sourceSubType, String bookingClass,
                       String depAPO, String arrAPO, String departureDate, String flightNumber, int mileage) {
        this.cancelledTransaction = cancelledTransaction;
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
    public Transaction cancelTransaction() {

        TransactionType nextTxnType;
        this.status = TransactionStatus.CANCELLED;

        if(this.txnType.equals(TransactionType.REDEMPTION)) {
            this.member.updateMember(this.mileage);
            nextTxnType = TransactionType.ACCRUAL;
        } else {
            this.member.updateMember(this.mileage*-1);
            nextTxnType = TransactionType.REDEMPTION;
        }

        return generateCancellationTxn(nextTxnType);
    }
    public Transaction generateCancellationTxn(TransactionType nextTxnType) {

        return Transaction.builder()
                .cancelledTransaction(this)
                .txnType(nextTxnType)
                .txnSubType(TransactionSubType.CANCELLATION)
                .depAPO(this.getDepAPO())
                .arrAPO(this.getArrAPO())
                .departureDate(this.getDepartureDate())
                .flightNumber(this.getFlightNumber())
                .bookingClass(this.getBookingClass())
                .mileage(this.getMileage())
                .member(this.getMember())
                .sourceType(this.getSourceType())
                .sourceSubType(this.getSourceSubType())
                .status(TransactionStatus.PROCESSED)
                .build();
    }

  ////  private TransactionStatus status; // PROCESSED, CANCELLED
}
