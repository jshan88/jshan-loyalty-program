package com.loyalty.jshan.transaction.controller;

import com.loyalty.jshan.global.apiResponse.ApiResponseWithSuccess;
import com.loyalty.jshan.transaction.domain.enums.SourceType;
import com.loyalty.jshan.transaction.domain.enums.TransactionType;
import com.loyalty.jshan.transaction.dto.TransactionRequestDto;
import com.loyalty.jshan.transaction.dto.TransactionResponseDto;
import com.loyalty.jshan.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/api/v1/transaction")
    public ApiResponseWithSuccess<List<TransactionResponseDto>> retrieveTransaction(@RequestParam("memberId") Long memberId,
                                                                                    @RequestParam("txnType") TransactionType txnType,
                                                                                    @RequestParam("sourceType") SourceType sourceType) {

        return ApiResponseWithSuccess.createApiResponse(transactionService.retrieveTransactionList(memberId, txnType, sourceType));
    }

}
