package com.loyalty.jshan.accrual.controller;

import com.loyalty.jshan.accrual.dto.AccrualRequestDto;
import com.loyalty.jshan.accrual.dto.AccrualResponseDto;
import com.loyalty.jshan.accrual.service.AccrualService;
import com.loyalty.jshan.global.response.ApiResponseWithSucess;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AccrualController {

    private final AccrualService accrualService;

    @PostMapping("/api/v1/accrual")
    public ApiResponseWithSucess<AccrualResponseDto> accrualRequest(@RequestBody AccrualRequestDto requestDto) {

        AccrualResponseDto responseDto = accrualService.postAccrualRequest(requestDto);         
        
        return ApiResponseWithSucess.createApiResponse(responseDto);      
        
    }
    @PutMapping("/api/v1/accrual/{accrualId}")
    public ResponseEntity<Long> accrualCancelRequest(@PathVariable Long accrualId) {

        Long id = accrualService.putAccrualCancelRequest(accrualId);
//        return accrualService.putAccrualCancelRequest(accrualId);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

}
