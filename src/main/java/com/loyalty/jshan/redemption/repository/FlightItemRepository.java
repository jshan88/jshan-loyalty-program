package com.loyalty.jshan.redemption.repository;

import com.loyalty.jshan.redemption.domain.item.flight.FlightItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightItemRepository extends JpaRepository<FlightItem, Long> {
}
