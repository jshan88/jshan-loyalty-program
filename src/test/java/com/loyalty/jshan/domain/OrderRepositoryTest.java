package com.loyalty.jshan.domain;

import com.loyalty.jshan.redemption.domain.Cart;
import com.loyalty.jshan.redemption.domain.item.flight.FlightItem;
import com.loyalty.jshan.redemption.domain.item.flight.FlightType;
import com.loyalty.jshan.redemption.repository.CartRepository;
import com.loyalty.jshan.redemption.repository.ItemRepository;
import com.loyalty.jshan.redemption.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

//    @AfterEach
//    public void deleteAll() {
//        orderRepository.deleteAll();
//        cartRepository.deleteAll();;
//        itemRepository.deleteAll();
//    }

    @Test
    public void setOrderRepository() {

        //given
        Cart cart = new Cart();
        FlightItem flightItem = FlightItem.builder().itemName("KE Int. 06JUL")
                .flightType(FlightType.INTERNATIONAL).depApo("ICN").arrApo("JFK").mileage(35000)
                .cart(cart).build();

        cart.addItemToCart(flightItem);

        System.out.println("##############");
        System.out.println(cartRepository.save(cart).getItemList());
        System.out.println(cart.getTotalCount());
        System.out.println(cart.getTotalMileage());
        System.out.println(cart.getItemList().get(0).getItemName());
        System.out.println("##############");
        //when

        //then

    }
}
