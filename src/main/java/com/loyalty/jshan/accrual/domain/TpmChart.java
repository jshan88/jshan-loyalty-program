package com.loyalty.jshan.accrual.domain;

import com.loyalty.jshan.global.CommonEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class TpmChart extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime effectiveFrom;

    private LocalDateTime effectiveTo;

    @Column(length = 3)
    private String depAPO;

    @Column(length = 3)
    private String arrAPO;

    private int tpmValue;

    @Builder
    public TpmChart(LocalDateTime effectiveFrom, LocalDateTime effectiveTo, String depAPO, String arrAPO, int tpmValue){
        this.effectiveFrom = effectiveFrom;
        this.effectiveTo = effectiveTo;
        this.depAPO = depAPO;
        this.arrAPO = arrAPO;
        this.tpmValue = tpmValue;
    }
}
