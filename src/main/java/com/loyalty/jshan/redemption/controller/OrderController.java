package com.loyalty.jshan.redemption.controller;

import com.loyalty.jshan.global.apiResponse.ApiResponseWithSuccess;
import com.loyalty.jshan.redemption.dto.OrderRequestDto;
import com.loyalty.jshan.redemption.dto.OrderResponseDto;
import com.loyalty.jshan.redemption.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/order")
    public ApiResponseWithSuccess<OrderResponseDto> orderRequest(@RequestBody OrderRequestDto requestDto){

        return ApiResponseWithSuccess.createApiResponse(orderService.issueOrder(requestDto));
    }

    @PutMapping("/api/v1/order/{authNumber}")
    public ApiResponseWithSuccess<OrderResponseDto> orderRefund(@PathVariable String authNumber) {

        return ApiResponseWithSuccess.createApiResponse(orderService.cancelOrder(authNumber));
    }
}
