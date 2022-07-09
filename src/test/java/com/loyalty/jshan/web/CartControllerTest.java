package com.loyalty.jshan.web;

import com.loyalty.jshan.redemption.dto.CartResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    private TestRestTemplate restTemplate;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartRepository cartRepository;


    @Transactional
    @Test
    public void cartResponseTest() {

        //given
        Long memberId = 1L;
        String url = "http://localhost:" + port + "/api/v1/cart/" + memberId;

        //when
        ResponseEntity<CartResponseDto> responseEntity = restTemplate.getForEntity(url, CartResponseDto.class);


        //then
        System.out.println(responseEntity);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isInstanceOf(CartResponseDto.class);


    }


    @Transactional
    @Test
    public void cartRequestTest() {  
        //given
        FlightItemRequestDto itemRequestDto = FlightItemRequestDto.builder()
                                                .itemName("KE INT FLT")
                                                .itemCount(1    )
                                                .flightType(FlightType.INTERNATIONAL)
                                                .depDate("20221001")
                                                .depApo("ICN")
                                                .arrApo("LAX")
                                                .mileage(35000)
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
