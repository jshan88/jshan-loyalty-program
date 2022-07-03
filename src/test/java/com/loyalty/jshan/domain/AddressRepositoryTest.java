package com.loyalty.jshan.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.loyalty.jshan.domain.address.Address;
import com.loyalty.jshan.domain.address.AddressRepository;
import com.loyalty.jshan.domain.address.enumForAddress.AddressType;

@SpringBootTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @AfterEach
    public void deleteAll() {
        addressRepository.deleteAll();
    }

    @Test
    public void insertAddress() {

        //given 
        

        //when
        Address address = Address.builder().addressType(AddressType.B).build();
        // addressRepository.save(address);

        //then
        System.out.println(addressRepository.save(address).getAddressType());
    }
    
}
