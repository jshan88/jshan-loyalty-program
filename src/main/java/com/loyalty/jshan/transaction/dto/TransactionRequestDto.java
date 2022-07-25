package com.loyalty.jshan.transaction.dto;

import com.loyalty.jshan.transaction.domain.enums.SourceType;
import com.loyalty.jshan.transaction.domain.enums.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TransactionRequestDto {

    private Long memberId;

    private TransactionType txnType; /// Accrual or Redemption

    private SourceType sourceType;    // All or Flight or Hotel or C.C or Others

    @Builder
    public TransactionRequestDto(Long memberId, TransactionType txnType, SourceType sourceType) {
        this.memberId = memberId;
        this.txnType = txnType;
        this.sourceType = sourceType;
    }
}
