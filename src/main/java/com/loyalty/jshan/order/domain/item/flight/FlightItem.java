package com.loyalty.jshan.order.domain.item.flight;

import com.loyalty.jshan.order.domain.item.Item;

import javax.persistence.Entity;


@Entity
//@DiscriminatorValue("F)
public class FlightItem extends Item {

    private FlightType flightType;


}
