package com.loyalty.jshan.redemption.service;

import com.loyalty.jshan.member.repository.MemberRepository;
import com.loyalty.jshan.redemption.dto.CartRequestDto;
import com.loyalty.jshan.redemption.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;

    public Long addItemToCart(CartRequestDto cartRequestDto) {

        Long memberId = cartRequestDto.getMemberId();

//       1. cartRepository.findBy MemberId(memberId)
//       2. if not null then use the existing cart,
//          if null then initiate a new cart. (by dto.toEntity)
//        3. dto.getFlightItem

        return 0L;
    }
}
