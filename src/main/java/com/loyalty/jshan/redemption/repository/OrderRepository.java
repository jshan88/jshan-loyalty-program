package com.loyalty.jshan.redemption.repository;

import com.loyalty.jshan.redemption.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByAuthNumber(String authNumber);

    @Query("select o from Order o where o.authNumber = ?1 and o.activeFlg = 'Y'")
    Optional<Order> findOrderByAuthNumber(String authNumber);
}
