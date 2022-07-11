package com.loyalty.jshan.accrual.controller;

import com.loyalty.jshan.accrual.dto.AccrualRequestDto;
import com.loyalty.jshan.accrual.service.AccrualService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccrualController {

    private final AccrualService accrualService;

    @PostMapping("/api/v1/accrual")
    public Long accrualRequest(@RequestBody AccrualRequestDto requestDto) {

        return accrualService.postAccrualRequest(requestDto);
    }
}
