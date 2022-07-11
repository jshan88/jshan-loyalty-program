package com.loyalty.jshan.accrual.controller;

import com.loyalty.jshan.accrual.dto.AccrualRequestDto;
import com.loyalty.jshan.accrual.service.AccrualService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AccrualController {

    private final AccrualService accrualService;

    @PostMapping("/api/v1/accrual")
    public ResponseEntity<Long> accrualRequest(@RequestBody AccrualRequestDto requestDto) {

        Long id = accrualService.postAccrualRequest(requestDto);
//        return accrualService.postAccrualRequest(requestDto); // returns transaction id.
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
    @PutMapping("/api/v1/accrual/{accrualId}")
    public ResponseEntity<Long> accrualCancelRequest(@PathVariable Long accrualId) throws Exception {

        Long id = accrualService.putAccrualCancelRequest(accrualId);
//        return accrualService.putAccrualCancelRequest(accrualId);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

}
