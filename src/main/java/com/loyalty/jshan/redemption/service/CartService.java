package com.loyalty.jshan.redemption.service;

import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.member.repository.MemberRepository;
import com.loyalty.jshan.redemption.domain.Cart;
import com.loyalty.jshan.redemption.domain.item.Item;
import com.loyalty.jshan.redemption.domain.item.flight.FlightItem;
import com.loyalty.jshan.redemption.dto.CartRequestDto;
import com.loyalty.jshan.redemption.dto.CartResponseDto;
import com.loyalty.jshan.redemption.dto.item.ItemResponseDto;
import com.loyalty.jshan.redemption.dto.item.flight.FlightItemRequestDto;
import com.loyalty.jshan.redemption.dto.item.flight.FlightItemResponseDto;
import com.loyalty.jshan.redemption.repository.CartRepository;
import com.loyalty.jshan.redemption.repository.FlightItemRepository;
import com.loyalty.jshan.redemption.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {

    private final FlightItemRepository flightItemRepository;
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository; 
    private final ItemService itemService;



    public CartResponseDto retrieveCartByMember(Long memberId) {

        Cart cart = cartRepository.findByMemberId(memberId);
        List<FlightItemResponseDto> flightList = new ArrayList<>();

        cart.getItemList().forEach(lst -> {
            FlightItem flightItem = flightItemRepository.findById(lst.getId()).orElse(null);
            flightList.add(itemService.retrieveFlightItemDto(flightItem));
        });

        return CartResponseDto.builder()
                .expiryDate(cart.getExpiryDate())
                .totalCount(cart.getTotalCount())
                .totalMileage(cart.getTotalMileage())
                .flightItemList(flightList)
                .build();
    }

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