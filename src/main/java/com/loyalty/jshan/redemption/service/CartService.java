package com.loyalty.jshan.redemption.service;

import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.member.repository.MemberRepository;
import com.loyalty.jshan.redemption.domain.Cart;
import com.loyalty.jshan.redemption.domain.item.flight.FlightItem;
import com.loyalty.jshan.redemption.dto.CartRequestDto;
import com.loyalty.jshan.redemption.dto.item.flight.FlightItemRequestDto;
import com.loyalty.jshan.redemption.repository.CartRepository;
import com.loyalty.jshan.redemption.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository; 
    private final ItemService itemService;

    @Transactional
    public Long updateCart(CartRequestDto cartRequestDto) {

        Long memberId = cartRequestDto.getMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new RuntimeException("No Member Found with the given ID"));
        Cart cart = cartRepository.findByMemberId(memberId);

        if(cart == null) {
            cart = cartRequestDto.toEntity(member); // initiate a new cart, if there's no existing cart. 
        };

        itemService.addFlightItemToCart(cart, cartRequestDto.getFlightItemList());
        return cartRepository.save(cart).getId(); 

    }
}
