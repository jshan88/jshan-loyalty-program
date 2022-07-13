package com.loyalty.jshan.accrual.controller;

import com.loyalty.jshan.accrual.dto.AccrualRequestDto;
import com.loyalty.jshan.accrual.dto.AccrualResponseDto;
import com.loyalty.jshan.accrual.service.AccrualService;
import com.loyalty.jshan.global.apiResponse.ApiResponseWithSuccess;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AccrualController {

    private final AccrualService accrualService;

    @PostMapping("/api/v1/accrual")
    public ApiResponseWithSuccess<AccrualResponseDto> accrualRequest(@RequestBody AccrualRequestDto requestDto) {

        AccrualResponseDto responseDto = accrualService.postAccrualRequest(requestDto);
        // ApiResponseWithSucess<AccrualResponseDto> apiResponseWithSucess = new ApiResponseWithSucess<>();
        // return apiResponseWithSucess.createApiResponse(responseDto);
        ///Changed below method to the static, as it will be broadly used without having instance variables inside. 
        return ApiResponseWithSuccess.createApiResponse(responseDto);

    }
    @PutMapping("/api/v1/accrual/{accrualId}")
    public ResponseEntity<Long> accrualCancelRequest(@PathVariable Long accrualId) {

        Long id = accrualService.putAccrualCancelRequest(accrualId);
//        return accrualService.putAccrualCancelRequest(accrualId);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

}
