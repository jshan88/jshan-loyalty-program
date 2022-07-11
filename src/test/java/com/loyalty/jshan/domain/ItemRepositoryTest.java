package com.loyalty.jshan.domain;

import com.loyalty.jshan.redemption.domain.item.Item;
import com.loyalty.jshan.redemption.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void itemEntityTest() {

        Item item = itemRepository.findById(1L).orElseThrow(()-> new RuntimeException("not exist"));



        System.out.println(item.getItemCount());

        System.out.println(item.getItemName());

    }
}
