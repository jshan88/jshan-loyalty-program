package com.loyalty.jshan.redemption.dto.item.flight;

import com.loyalty.jshan.redemption.domain.item.flight.FlightType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FlightItemResponseDto {

    private Long id;
    private String itemName;
    private int itemCount;
    private int mileage;
    private FlightType flightType;
    private String mktCarrier;
    private String oprCarrier;
    private String depDate;
    private String depApo;
    private String arrApo;

    @Builder
    public FlightItemResponseDto(Long id, String itemName, int itemCount, int mileage,
                                 FlightType flightType, String mktCarrier, String oprCarrier,
                                 String depDate, String depApo, String arrApo) {
        this.id = id;
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.mileage = mileage;
        this.flightType = flightType;
        this.mktCarrier = mktCarrier;
        this.oprCarrier = oprCarrier;
        this.depDate = depDate;
        this.depApo = depApo;
        this.arrApo = arrApo;
    }
}
