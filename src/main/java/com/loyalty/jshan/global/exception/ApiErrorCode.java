package com.loyalty.jshan.global.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public enum ApiErrorCode {
    
    // 400 BAD REQUEST
    ACCRUAL_ALREADY_CANCELLED(HttpStatus.BAD_REQUEST, "Accrual has been cancelled already."),
    WRONG_TRANSACTION_TYPE(HttpStatus.BAD_REQUEST, "Action not allowed with the given transaction type"),
    
    // 404 NOT FOUND
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "No member found with the given id."),
    TRANSACTION_NOT_FOUND(HttpStatus.NOT_FOUND, "No transaction found with the given id."),
    ACCRUAL_RATE_NOT_FOUND(HttpStatus.NOT_FOUND, "No accrual rate found with the given carrier and booking class."),
    TPM_NOT_FOUND(HttpStatus.NOT_FOUND, "No tpm value found with the given segment."),

    ;

    private final HttpStatus httpStatus; 
    private final String description;
}
