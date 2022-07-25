package com.loyalty.jshan.global.apiException;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public enum ApiErrorCode {
    // 400 BAD REQUEST
    NOT_ENOUGH_MILEAGE(HttpStatus.BAD_REQUEST, "Member does not have enough miles to redeem."),
    
    // 404 NOT FOUND
    TXN_NOT_FOUND(HttpStatus.NOT_FOUND, "No Transaction Found."),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "No Order found with the given auth"),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "No member found with the given id."),
    CART_NOT_FOUND(HttpStatus.NOT_FOUND, "No cart found with the given id."),
    TRANSACTION_NOT_FOUND(HttpStatus.NOT_FOUND, "No transaction found with the given id."),
    ACCRUAL_RATE_NOT_FOUND(HttpStatus.NOT_FOUND, "No accrual rate found with the given carrier and booking class."),
    TPM_NOT_FOUND(HttpStatus.NOT_FOUND, "No tpm value found with the given segment."),

    // 409 CONFLICT
    ACCRUAL_ALREADY_CANCELLED(HttpStatus.CONFLICT, "Accrual has been cancelled already."),
    ORDER_ALREADY_CANCELLED(HttpStatus.CONFLICT, "Auth has been cancelled already."),
    WRONG_TRANSACTION_TYPE(HttpStatus.CONFLICT, "Action not allowed with the given transaction type"),

    ;

    private final HttpStatus httpStatus; 
    private final String description;
}
