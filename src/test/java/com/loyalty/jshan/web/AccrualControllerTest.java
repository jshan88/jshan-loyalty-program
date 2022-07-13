package com.loyalty.jshan.web;

import com.loyalty.jshan.accrual.domain.AccrualRateChart;
import com.loyalty.jshan.accrual.domain.TpmChart;
import com.loyalty.jshan.accrual.dto.AccrualRequestDto;
import com.loyalty.jshan.accrual.dto.FlightAccrualRequestDto;
import com.loyalty.jshan.accrual.repository.AccrualRateChartRepository;
import com.loyalty.jshan.accrual.repository.TpmChartRepository;
import com.loyalty.jshan.transaction.domain.Transaction;
import com.loyalty.jshan.transaction.repository.TransactionRepository;

import io.swagger.models.Response;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccrualControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccrualRateChartRepository accrualRateChartRepository;

    @Autowired
    private TpmChartRepository tpmChartRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void putAccrualCancelTest() {

        //given
        Long accrualId = 6L;
        String url = "http://localhost:" + port + "/api/v1/accrual/" + accrualId;
        HttpEntity<Long> requestEntity = new HttpEntity<>(accrualId);

        //when
        ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Object.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Transaction> transactionList = transactionRepository.findAll();
        transactionList.forEach(txn -> {
            System.out.println(txn.getId() + " " + txn.getTxnType() + " " + txn.getTxnSubType() + " " + txn.getStatus());
        });

    }


    @Transactional
    @Test
    public void postAccrualTest() {

        FlightAccrualRequestDto flightRequestDto = FlightAccrualRequestDto.builder()
                .carrier("KE")
                .depDate("20220811")
                .depAPO("JFK")
                .arrAPO("ICN")
                .bookingClass("J")
                .build();

        AccrualRequestDto accrualRequestDto = AccrualRequestDto.builder()
                .memberId(1L)
                .flightRequest(flightRequestDto)
                .build();

        String url = "http://localhost:" + port + "/api/v1/accrual";

        //when
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(url, accrualRequestDto, Object.class);
        // ResponseEntity responseEntity = restTemplate.postForEntity(url, accrualRequestDto, ResponseEntity.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        // assertThat(responseEntity.getBody().)
        // assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Transaction> transactionList = transactionRepository.findAll();

        System.out.println(transactionList.get(0).getMileage());


    }

}
