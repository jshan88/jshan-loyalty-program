package com.loyalty.jshan.domain;

import com.loyalty.jshan.member.domain.address.enumAddress.AddressType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.loyalty.jshan.member.domain.address.Address;
import com.loyalty.jshan.member.domain.address.AddressRepository;

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
