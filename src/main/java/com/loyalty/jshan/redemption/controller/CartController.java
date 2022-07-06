package com.loyalty.jshan.redemption.controller;

import com.loyalty.jshan.redemption.dto.CartRequestDto;
import com.loyalty.jshan.redemption.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CartController {

    private final CartService cartService;

    @PostMapping("/api/v1/cart")
    public Long addItemToCart(@RequestBody CartRequestDto requestDto) {

        return 0L;
    }
}
