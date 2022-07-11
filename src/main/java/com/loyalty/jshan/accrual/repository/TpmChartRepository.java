package com.loyalty.jshan.accrual.repository;

import com.loyalty.jshan.accrual.domain.TpmChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TpmChartRepository extends JpaRepository<TpmChart, Long> {

    @Query("select t from TpmChart t where t.depAPO = ?1 and t.arrAPO = ?2")
    public TpmChart findTpmValueBySegment(String depAPO, String arrAPO);
}
