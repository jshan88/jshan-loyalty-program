package com.loyalty.jshan.accrual.service;

import com.loyalty.jshan.accrual.domain.AccrualRateChart;
import com.loyalty.jshan.accrual.domain.TpmChart;
import com.loyalty.jshan.accrual.dto.AccrualRequestDto;
import com.loyalty.jshan.accrual.dto.AccrualResponseDto;
import com.loyalty.jshan.accrual.dto.FlightAccrualRequestDto;
import com.loyalty.jshan.accrual.dto.FlightAccrualResponseDto;
import com.loyalty.jshan.global.apiException.ApiErrorCode;
import com.loyalty.jshan.global.apiException.ApiRequestException;
import com.loyalty.jshan.accrual.repository.AccrualRateChartRepository;
import com.loyalty.jshan.accrual.repository.TpmChartRepository;
import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.member.repository.MemberRepository;
import com.loyalty.jshan.transaction.domain.*;
import com.loyalty.jshan.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class AccrualService {

    private final AccrualRateChartRepository accrualRateChartRepository;
    private final TpmChartRepository tpmChartRepository;
    private final MemberRepository memberRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public Long putAccrualCancelRequest(Long accrualId) {

        Transaction cancelTxn = transactionRepository.findById(accrualId)
                .orElseThrow(()-> new ApiRequestException(ApiErrorCode.TRANSACTION_NOT_FOUND));
        
        if(!cancelTxn.getTxnType().equals(TransactionType.ACCRUAL)) { 
            throw new ApiRequestException(ApiErrorCode.WRONG_TRANSACTION_TYPE);
        }
        if(cancelTxn.getStatus() == TransactionStatus.CANCELLED) {
            throw new ApiRequestException(ApiErrorCode.ACCRUAL_ALREADY_CANCELLED);
        }

        Transaction cancellationTxn = cancelTxn.cancelTransaction();

        return transactionRepository.save(cancellationTxn).getId();
    }

    public AccrualResponseDto postAccrualRequest(AccrualRequestDto accrualRequestDto)  {

        Long memberId = accrualRequestDto.getMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow(()
                -> new ApiRequestException(ApiErrorCode.MEMBER_NOT_FOUND));

        //TODO : considered the flight accrual request only for now.
        FlightAccrualResponseDto flightResponse = postFlightAccrualRequest(member, accrualRequestDto.getFlightRequest());

        return AccrualResponseDto.builder()
                                .flightResponse(flightResponse)
                                .memberId(member.getId())
                                .build();
    }

    @Transactional
    public FlightAccrualResponseDto postFlightAccrualRequest(Member member, FlightAccrualRequestDto requestDto) {

        //TODO : dupe check. create a new method in transactionRepository .
        int accruedMileage = getMileageToAccrue(requestDto.getCarrier(), requestDto.getBookingClass(),
                                                requestDto.getDepAPO(), requestDto.getArrAPO());

        //TODO : Status to be "In-progress",
        //  if there's an engine that runs at the back and process those that are in-progress status
        Transaction txn = Transaction.builder()
                                .member(member)
                                .txnType(TransactionType.ACCRUAL)
                                .txnSubType(TransactionSubType.PRODUCT)
                                .status(TransactionStatus.PROCESSED)
                                .sourceType(SourceType.FLIGHT)
                                .sourceSubType(requestDto.getCarrier())
                                .bookingClass(requestDto.getBookingClass())
                                .depAPO(requestDto.getDepAPO())
                                .arrAPO(requestDto.getArrAPO())
                                .departureDate(requestDto.getDepDate())
                                .flightNumber(requestDto.getFlightNumber())
                                .mileage(accruedMileage)
                                .build();

        member.updateMember(accruedMileage);
        transactionRepository.save(txn);

        return FlightAccrualResponseDto.builder().carrier(txn.getSourceSubType()).bookingClass(txn.getBookingClass())
                                                .depAPO(txn.getDepAPO()).arrAPO(txn.getArrAPO()).depDate(txn.getDepartureDate())
                                                .flightNumber(txn.getFlightNumber())
                                                .build(); 
    }

    public int getMileageToAccrue(String carrier, String bookingClass, String depApo, String arrApo) {
        int tpmValue = getTpmValue(depApo, arrApo);
        int accrualRate = getAccrualRate(carrier, bookingClass);

        return Math.round(tpmValue * accrualRate / 100);
    }
    public int getAccrualRate(String carrier, String bookingClass) {
        AccrualRateChart bookingClassChart = accrualRateChartRepository.findAccrualRateByClass(carrier, bookingClass);

        if(bookingClassChart == null) { 
            throw new ApiRequestException(ApiErrorCode.ACCRUAL_RATE_NOT_FOUND);
        }

        return bookingClassChart.getAccrualRate();
    }
    public int getTpmValue(String depAPO, String arrAPO) {
        //TODO : when invoking tpmChartRepostiory.findBySegment, pass the current date as a parameter
        //       to get the active TPM at the moment.
        TpmChart tpmChart = tpmChartRepository.findTpmValueBySegment(depAPO, arrAPO);

        if(tpmChart == null) { 
            throw new ApiRequestException(ApiErrorCode.TPM_NOT_FOUND);
        }

        return tpmChart.getTpmValue();
    }
}
