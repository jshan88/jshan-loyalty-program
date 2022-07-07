package com.loyalty.jshan.redemption.domain.item.flight;
import com.loyalty.jshan.redemption.domain.Cart;
import com.loyalty.jshan.redemption.domain.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor
@Entity 
public class FlightItem extends Item {

    @Enumerated(EnumType.STRING)
    private FlightType flightType;

    @Column(length = 2)
    private String mktCarrier;

    @Column(length = 2)
    private String oprCarrier;

    @Column(length = 8)
    private String depDate;

    @Column(length = 3)
    private String depApo;

    @Column(length = 3)
    private String arrApo;

    @Builder
    public FlightItem(String itemName, int itemCount, int mileage, Cart cart, FlightType flightType,
                      String mktCarrier, String oprCarrier, String depDate, String depApo, String arrApo) {
        super(itemName, itemCount, mileage, cart);
        this.flightType = flightType;
        this.mktCarrier = mktCarrier;
        this.oprCarrier = oprCarrier;
        this.depDate = depDate;
        this.depApo = depApo;
        this.arrApo = arrApo;
    }
}
