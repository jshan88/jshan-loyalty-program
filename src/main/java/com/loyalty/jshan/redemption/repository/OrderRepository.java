package com.loyalty.jshan.redemption.repository;

import com.loyalty.jshan.redemption.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
