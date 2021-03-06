package com.loyalty.jshan.redemption.repository;

import com.loyalty.jshan.redemption.domain.item.Item;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

//    @Query("select i, f from Item i join FlightItem where i.cartId = ?1 and i.itemName = ?2")
//    Item findItemInCart(Long cartId, String itemName);

    @Query(value = "SELECT * " +
            "FROM Item i, Flight_Item f " +
            "WHERE 1=1 " +
            "and i.id = f.id " +
            "and i.cart_Id = ?1 " +
            "and i.item_Name = ?2", nativeQuery = true)
    Item findSpecificItemInCart(Long cartId, String itemName);
}
