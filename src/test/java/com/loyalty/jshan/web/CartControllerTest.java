package com.loyalty.jshan.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.loyalty.jshan.redemption.domain.item.Item;
import com.loyalty.jshan.redemption.domain.item.flight.FlightType;
import com.loyalty.jshan.redemption.dto.CartRequestDto;
import com.loyalty.jshan.redemption.dto.item.flight.FlightItemRequestDto;
import com.loyalty.jshan.redemption.repository.CartRepository;
import com.loyalty.jshan.redemption.repository.ItemRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CartRepository cartRepository;

    @Transactional
    @Test
    public void cartRequestTest() {  
        //given
        FlightItemRequestDto itemRequestDto = FlightItemRequestDto.builder()
                                                .itemName("KE DOM FLT")
                                                .itemCount(1)
                                                .flightType(FlightType.DOMESTIC)
                                                .depDate("20220901")
                                                .depApo("GMP")
                                                .arrApo("CJU")
                                                .mileage(5000)
                                                .mktCarrier("KE")
                                                .oprCarrier("KE")
                                                .build();

        List<FlightItemRequestDto> flightItemList = new ArrayList<>();
        flightItemList.add(itemRequestDto);
        
        CartRequestDto cartRequestDto = CartRequestDto.builder()
                                            .memberId(1L)
                                            .flightItemList(flightItemList)
                                            .build();

        String url = "http://localhost:" + port + "/api/v1/cart"; 


        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, cartRequestDto, Long.class); 



        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
    
}
