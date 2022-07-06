package com.loyalty.jshan.order.repository;

import com.loyalty.jshan.order.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
