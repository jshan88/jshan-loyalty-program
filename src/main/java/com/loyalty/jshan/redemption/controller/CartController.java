package com.loyalty.jshan.redemption.controller;

import com.loyalty.jshan.redemption.dto.CartResponseDto;
import com.loyalty.jshan.global.apiResponse.ApiResponseWithSuccess;
import com.loyalty.jshan.redemption.dto.CartRequestDto;
import com.loyalty.jshan.redemption.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CartController {

    private final CartService cartService;

    @GetMapping("/api/v1/cart/{memberId}")
    public ApiResponseWithSuccess<CartResponseDto> retrieveCart(@PathVariable Long memberId) {

        return ApiResponseWithSuccess.createApiResponse(cartService.retrieveCartByMember(memberId));
    }

    @PostMapping("/api/v1/cart")
    public ApiResponseWithSuccess<CartResponseDto> addItemToCart(@RequestBody CartRequestDto requestDto) {

        return ApiResponseWithSuccess.createApiResponse(cartService.updateCart(requestDto));
    }


}
