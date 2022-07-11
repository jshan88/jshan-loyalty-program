package com.loyalty.jshan.redemption.controller;

import com.loyalty.jshan.redemption.dto.CartResponseDto;
import com.loyalty.jshan.redemption.dto.CartRequestDto;
import com.loyalty.jshan.redemption.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CartController {

    private final CartService cartService;

    @GetMapping("/api/v1/cart/{memberId}")
    public CartResponseDto retrieveCart(@PathVariable Long memberId) {

        return cartService.retrieveCartByMember(memberId);
    }

    @PostMapping("/api/v1/cart")
    public Long addItemToCart(@RequestBody CartRequestDto requestDto) {

        return cartService.updateCart(requestDto);
    }


}
