package com.loyalty.jshan.accrual.domain;

import com.loyalty.jshan.global.CommonEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class AccrualRateChart extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String carrier;

    @Column(length = 1)
    private String bookingClass;

    private int accrualRate;

    @Builder
    public AccrualRateChart(String carrier, String bookingClass, int accrualRate) {
        this.carrier = carrier;
        this.bookingClass = bookingClass;
        this.accrualRate = accrualRate;
    }
}
