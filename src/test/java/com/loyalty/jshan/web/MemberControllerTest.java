package com.loyalty.jshan.web;

import com.loyalty.jshan.domain.address.enumForAddress.AddressType;
import com.loyalty.jshan.domain.contact.ContactRepository;
import com.loyalty.jshan.domain.member.Member;
import com.loyalty.jshan.domain.member.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.loyalty.jshan.web.dto.address.AddressEnrollmentDto;
import com.loyalty.jshan.web.dto.contact.ContactEnrollmentDto;
import com.loyalty.jshan.web.dto.member.MemberEnrollmentDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ContactRepository contactRepository;

    @AfterEach
    public void deleteAll() {
        contactRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    public void saveMemberTest() {

        //given
        String firstName = "JINSEOK";
        String lastName = "HAN";
        String mobileNumber = "010-2221-2509";
        String homePhoneNumber = "032-323-2509";
        String emailAddress = "jshan88@gmail.com";
        AddressType addressType = AddressType.H;
        String zipCode = "420-849";
        String country = "Korea";
        String address1 = "Goyang-si Dongsong-ro";
        String address2 = "305-1502";

        AddressEnrollmentDto addressDto = AddressEnrollmentDto.builder()
                .addressType(addressType)
                .zipCode(zipCode)
                .country(country)
                .address1(address1)
                .address2(address2)
                .build();

        ContactEnrollmentDto contactDto = ContactEnrollmentDto.builder()
                .mobileNumber(mobileNumber)
                .homePhoneNumber(homePhoneNumber)
                .emailAddress(emailAddress)
                .build();

        MemberEnrollmentDto memberDto = MemberEnrollmentDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .contactInfo(contactDto)
                .addressInfo(addressDto)
                .build();

        String url = "http://localhost:" + port + "/api/v1/member";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, memberDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Member> members = memberRepository.findAll();

        assertThat(members.get(0).getFirstName()).isEqualTo(firstName);
        assertThat(members.get(0).getContact().getEmailAddress()).isEqualTo(emailAddress);
        assertThat(members.get(0).getAddress().getAddressType()).isEqualTo(AddressType.H);
    }
}
