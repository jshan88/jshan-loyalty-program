package com.loyalty.jshan.domain.order.item.flight;

import com.loyalty.jshan.domain.order.item.Item;

import javax.persistence.Entity;


@Entity
public class FlightItem extends Item {

    private FlightType flightType;


}
