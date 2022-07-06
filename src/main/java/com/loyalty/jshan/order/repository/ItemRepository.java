package com.loyalty.jshan.order.repository;

import com.loyalty.jshan.order.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
