package com.loyalty.jshan.accrual.repository;

import com.loyalty.jshan.accrual.domain.AccrualRateChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccrualRateChartRepository extends JpaRepository<AccrualRateChart, Long> {

    @Query("select b from AccrualRateChart b where b.carrier = ?1 and b.bookingClass = ?2")
    public AccrualRateChart findAccrualRateByClass(String carrier, String bookingClass);
}
