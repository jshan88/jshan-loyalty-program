package com.loyalty.jshan.domain;

import com.loyalty.jshan.global.apiException.ApiErrorCode;
import com.loyalty.jshan.global.apiException.ApiRequestException;
import com.loyalty.jshan.redemption.domain.Cart;
import com.loyalty.jshan.redemption.domain.item.Item;
import com.loyalty.jshan.redemption.repository.CartRepository;
import com.loyalty.jshan.redemption.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void itemEntityTest() {

        Item item = itemRepository.findById(1L).orElseThrow(()-> new RuntimeException("not exist"));

        System.out.println(item.getItemCount());

        System.out.println(item.getItemName());

    }

    @Test
    public void cartEntityTest(){

        Cart cart = cartRepository.findByMemberId(1L);

        System.out.println(cart.getId());

    }


}
