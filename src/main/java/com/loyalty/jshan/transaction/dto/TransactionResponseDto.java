package com.loyalty.jshan.transaction.dto;

import com.loyalty.jshan.transaction.domain.enums.SourceType;
import com.loyalty.jshan.transaction.domain.enums.TransactionStatus;
import com.loyalty.jshan.transaction.domain.enums.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TransactionResponseDto {

    private Long id;
    private Long memberId;
    private TransactionType txnType;
    private SourceType sourceType;
    private String sourceSubType;
    private TransactionStatus status;
    private String authNumber;
    private String bookingClass;
    private String depAPO;
    private String arrAPO;
    private String departureDate;
    private String flightNumber;
    private int mileage;

    @Builder
    public TransactionResponseDto(Long id, Long memberId, TransactionType txnType, SourceType sourceType,
                                  String sourceSubType, TransactionStatus status, String authNumber,
                                  String bookingClass, String depAPO, String arrAPO, String departureDate,
                                  String flightNumber, int mileage) {
        this.id = id;
        this.memberId = memberId;
        this.txnType = txnType;
        this.sourceType = sourceType;
        this.sourceSubType = sourceSubType;
        this.status = status;
        this.authNumber = authNumber;
        this.bookingClass = bookingClass;
        this.depAPO = depAPO;
        this.arrAPO = arrAPO;
        this.departureDate = departureDate;
        this.flightNumber = flightNumber;
        this.mileage = mileage;
    }
}
