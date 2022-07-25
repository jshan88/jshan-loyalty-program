package com.loyalty.jshan.transaction.service;

import com.loyalty.jshan.global.apiException.ApiErrorCode;
import com.loyalty.jshan.global.apiException.ApiRequestException;
import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.member.repository.MemberRepository;
import com.loyalty.jshan.redemption.repository.OrderRepository;
import com.loyalty.jshan.transaction.domain.Transaction;
import com.loyalty.jshan.transaction.domain.enums.SourceType;
import com.loyalty.jshan.transaction.domain.enums.TransactionSubType;
import com.loyalty.jshan.transaction.domain.enums.TransactionType;
import com.loyalty.jshan.transaction.dto.TransactionRequestDto;
import com.loyalty.jshan.transaction.dto.TransactionResponseDto;
import com.loyalty.jshan.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    public List<TransactionResponseDto> retrieveTransactionList(Long memberId, TransactionType txnType, SourceType sourceType) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new ApiRequestException(ApiErrorCode.MEMBER_NOT_FOUND));

        List<Transaction> txnList = transactionRepository.findTransactionListBy(member, txnType, TransactionSubType.PRODUCT, sourceType);

        if(txnList.size() == 0)
            throw new ApiRequestException(ApiErrorCode.TRANSACTION_NOT_FOUND);

        List<TransactionResponseDto> responseDtoList = new ArrayList<>();

        txnList.forEach(
                txn -> responseDtoList.add(toTransactionResponse(txn))
        );

        return responseDtoList;
    }

    private TransactionResponseDto toTransactionResponse(Transaction txn) {

        String authNumber = "";

        if(txn.getTxnType().equals(TransactionType.REDEMPTION))
            authNumber = txn.getOrder().getAuthNumber();

        return TransactionResponseDto.builder()
                .id(txn.getId())
                .memberId(txn.getMember().getId())
                .txnType(txn.getTxnType())
                .sourceType(txn.getSourceType())
                .sourceSubType(txn.getSourceSubType())
                .status(txn.getStatus())
                .authNumber(authNumber)
                .bookingClass(txn.getBookingClass())
                .depAPO(txn.getDepAPO())
                .arrAPO(txn.getArrAPO())
                .departureDate(txn.getDepartureDate())
                .flightNumber(txn.getFlightNumber())
                .mileage(txn.getMileage())
                .build();
    }
}
