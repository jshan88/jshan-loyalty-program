package com.loyalty.jshan.accrual.service;

import com.loyalty.jshan.accrual.domain.AccrualRateChart;
import com.loyalty.jshan.accrual.domain.TpmChart;
import com.loyalty.jshan.accrual.dto.AccrualRequestDto;
import com.loyalty.jshan.accrual.dto.FlightAccrualRequestDto;
import com.loyalty.jshan.global.exception.ApiRequestException;
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
                .orElseThrow(()-> new ApiRequestException("No transaction found with the given ID : " + accrualId));

        //TODO : create user-defined exception class.
        if(cancelTxn.getStatus() == TransactionStatus.CANCELLED) {
            throw new ApiRequestException("Transaction Already Cancelled");
        }
        cancelTxn.cancelTransaction();

        Transaction cancellationTxn = Transaction.builder()
                .cancelTransaction(cancelTxn)
                .txnType(TransactionType.REDEMPTION)
                .txnSubType(TransactionSubType.CANCELLATION)
                .depAPO(cancelTxn.getDepAPO())
                .arrAPO(cancelTxn.getArrAPO())
                .departureDate(cancelTxn.getDepartureDate())
                .flightNumber(cancelTxn.getFlightNumber())
                .bookingClass(cancelTxn.getBookingClass())
                .mileage(cancelTxn.getMileage())
                .member(cancelTxn.getMember())
                .sourceType(cancelTxn.getSourceType())
                .sourceSubType(cancelTxn.getSourceSubType())
                .status(TransactionStatus.PROCESSED)
                .build();

        return transactionRepository.save(cancellationTxn).getId();
    }

    public Long postAccrualRequest(AccrualRequestDto accrualRequestDto)  {

        Long memberId = accrualRequestDto.getMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow(()
                -> new ApiRequestException("no member found with the given id : " + memberId));

        //TODO : considered the flight accrual request only for now.
        return postFlightAccrualRequest(member, accrualRequestDto.getFlightRequest());
    }

    @Transactional
    public Long postFlightAccrualRequest(Member member, FlightAccrualRequestDto requestDto) {

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

        return txn.getId();
    }

    public int getMileageToAccrue(String carrier, String bookingClass, String depApo, String arrApo) {
        int tpmValue = getTpmValue(depApo, arrApo);
        int accrualRate = getAccrualRate(carrier, bookingClass);

        return Math.round(tpmValue * accrualRate / 100);
    }
    public int getAccrualRate(String carrier, String bookingClass) {
        AccrualRateChart bookingClassChart = accrualRateChartRepository.findAccrualRateByClass(carrier, bookingClass);

        return bookingClassChart.getAccrualRate();
    }
    public int getTpmValue(String depAPO, String arrAPO) {
        //TODO : when invoking tpmChartRepostiory.findBySegment, pass the current date as a parameter
        //       to get the active TPM at the moment.
        TpmChart tpmChart = tpmChartRepository.findTpmValueBySegment(depAPO, arrAPO);

        return tpmChart.getTpmValue();
    }
}
