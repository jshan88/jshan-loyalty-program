package com.loyalty.jshan.order.repository;

import com.loyalty.jshan.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
