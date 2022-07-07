package com.loyalty.jshan.redemption.repository;

import com.loyalty.jshan.redemption.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
