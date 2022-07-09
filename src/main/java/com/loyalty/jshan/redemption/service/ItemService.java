package com.loyalty.jshan.redemption.service;

import java.util.List;

import com.loyalty.jshan.redemption.dto.item.flight.FlightItemResponseDto;
import org.springframework.stereotype.Service;

import com.loyalty.jshan.redemption.domain.Cart;
import com.loyalty.jshan.redemption.domain.item.Item;
import com.loyalty.jshan.redemption.domain.item.flight.FlightItem;
import com.loyalty.jshan.redemption.dto.item.flight.FlightItemRequestDto;
import com.loyalty.jshan.redemption.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemService {
    
    private final ItemRepository itemRepository;

    public void addFlightItemToCart(Cart cart, List<FlightItemRequestDto> flightList) {         

        //flightList.stream().forEach(flightDto -> {
        flightList.forEach(flightDto -> {
            Item item = itemRepository.findSpecificItemInCart(cart.getId(), flightDto.getItemName()); /// 이거 HashMap<Item> 같은걸로 받아서 처리하는거 고민해보자. 

            if(item == null) {   /// add new item
                FlightItem flightItem = flightDto.toEntity();
                itemRepository.save(flightItem);
                cart.addItemToCart(flightItem);
            } else {  /// add more quantity of the existing item.                 
                item.addMoreItems(flightDto.getItemCount());
                cart.refreshCart();
            }            
        });
    }

    public FlightItemResponseDto retrieveFlightItemDto(FlightItem flightItem) {

        return FlightItemResponseDto.builder()
                .id(flightItem.getId())
                .itemName(flightItem.getItemName())
                .flightType(flightItem.getFlightType())
                .itemCount(flightItem.getItemCount())
                .arrApo(flightItem.getArrApo())
                .depApo(flightItem.getDepApo())
                .depDate(flightItem.getDepDate())
                .mileage(flightItem.getMileage())
                .mktCarrier(flightItem.getMktCarrier())
                .oprCarrier(flightItem.getOprCarrier())
                .build();
    }
}
