package com.loyalty.jshan.redemption.repository;

import com.loyalty.jshan.redemption.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
