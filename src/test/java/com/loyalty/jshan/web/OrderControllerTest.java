package com.loyalty.jshan.web;

import com.loyalty.jshan.global.apiResponse.ApiResponseWithSuccess;
import com.loyalty.jshan.redemption.dto.OrderRequestDto;
import com.loyalty.jshan.redemption.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

    @LocalServerPort
    private int port;


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    @Test
    public void orderIssueTest() {

        //given
        OrderRequestDto requestDto = OrderRequestDto.builder()
                .memberId(1L)
                .cartId(2L)
                .build();

        String url = "http://localhost:" + port + "/api/v1/order";

        //when
        ResponseEntity<ApiResponseWithSuccess> responseEntity = restTemplate.postForEntity(url, requestDto, ApiResponseWithSuccess.class);

        //then
        assertThat(responseEntity.getBody().getStatus()).isEqualTo(HttpStatus.OK.value());
        System.out.println(responseEntity.getBody().getData().toString());
    }

    @Transactional
    @Test
    public void orderRefundTest() {

        //given
        String authNumber = "2022A0724224903985";

        String url = "http://localhost:" + port + "/api/v1/order/" + authNumber;
        HttpEntity<String> requestEntity = new HttpEntity<>(authNumber);

        //when
        ResponseEntity<ApiResponseWithSuccess> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, ApiResponseWithSuccess.class);

        //then
        assertThat(responseEntity.getBody().getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
