package com.loyalty.jshan.domain;

import com.loyalty.jshan.accrual.domain.AccrualRateChart;
import com.loyalty.jshan.accrual.domain.TpmChart;
import com.loyalty.jshan.accrual.repository.AccrualRateChartRepository;
import com.loyalty.jshan.accrual.repository.TpmChartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class TpmRepositoryTest {

    @Autowired
    private TpmChartRepository tpmChartRepository;

    @Autowired
    private AccrualRateChartRepository accrualRateChartRepository;

    @Test
    public void tpmInsertTest() {

        TpmChart tpmChart = TpmChart.builder()
                .effectiveFrom(LocalDateTime.of(2022, 06, 01, 00, 00))
                .effectiveTo(LocalDateTime.of(2023, 06, 12, 00, 00))
                .depAPO("ICN")
                .arrAPO("JFK")
                .tpmValue(6316)
                .build();

        tpmChartRepository.save(tpmChart);
    }

    @Test
    public void accrualRateInsertTest () {

        AccrualRateChart accrualRateChart = AccrualRateChart.builder()
                .bookingClass("J")
                .accrualRate(120)
                .carrier("KE")
                .build();

        accrualRateChartRepository.save(accrualRateChart);
    }

}
