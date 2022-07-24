package com.loyalty.jshan.web;

import com.loyalty.jshan.member.domain.address.Address;
import com.loyalty.jshan.member.domain.address.AddressRepository;
import com.loyalty.jshan.member.domain.address.enums.AddressType;
import com.loyalty.jshan.member.domain.contact.Contact;
import com.loyalty.jshan.member.domain.contact.ContactRepository;
import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.member.repository.MemberRepository;
import com.loyalty.jshan.member.dto.MemberUpdateDto;
import com.loyalty.jshan.member.dto.address.AddressEnrollmentDto;
import com.loyalty.jshan.member.dto.MemberResponseDto;
import com.loyalty.jshan.member.dto.address.AddressUpdateDto;
import com.loyalty.jshan.member.dto.contact.ContactUpdateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.loyalty.jshan.member.dto.contact.ContactEnrollmentDto;
import com.loyalty.jshan.member.dto.MemberEnrollmentDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private AddressRepository addressRepository;

//    @AfterEach
//    public void deleteAll() {
//        contactRepository.deleteAll();
//        memberRepository.deleteAll();
//        addressRepository.deleteAll();
//    }

    @Test
    public void retrieveMemberTest() {

        //given
        Long id = 1L;

        String url = "http://localhost:" + port + "/api/v1/member/" + id;

        //when
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(url, Object.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        // System.out.println(responseEntity.g);
        // System.out.println(responseEntity.getBody().getContactResponseDto().getEmailAddress());
        // System.out.println(responseEntity.getBody().getAddressResponseDtoList().get(0).getAddressType());

    }

    @Transactional
    @Test
    public void updateMemberTest() {

        //given

        ContactUpdateDto contactUpdateDto = ContactUpdateDto.builder()
                .mobileNumber("010-6313-8116").build();

        AddressUpdateDto addressUpdateDto = AddressUpdateDto.builder()
                .addressType(AddressType.valueOf("B")).country("USA").build();

        List<AddressUpdateDto> addressList = new ArrayList<>();
        addressList.add(addressUpdateDto);

        MemberUpdateDto memberUpdateDto = MemberUpdateDto.builder()
                .firstName("JAYSON")
                .lastName("HAN")
                .contactUpdateDto(contactUpdateDto)
                .addressUpdateDtoList(addressList)
                .build();

        Long id = 1L;

        String url = "http://localhost:"+port+"/api/v1/member/" + id;

        HttpEntity<MemberUpdateDto> requestEntity = new HttpEntity<>(memberUpdateDto);

        //when

        ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Object.class);
        //then

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        memberRepository.findAll();
        addressRepository.findAll();
    }

    @Transactional
    @Test
    public void saveMemberTest() {

        //given

        //Member
        String firstName = "HAEJUN";
        String lastName = "HAN";
        //Contact
        String mobileNumber = "010-2221-2509";
        String homePhoneNumber = "032-323-2509";
        String emailAddress = "jshan88@gmail.com";
        //Address
        AddressType addressType = AddressType.H;
        String zipCode = "420-849";
        String country = "KOREA";
        String address1 = "Goyang-si";
        String address2 = "305-1502";

        AddressEnrollmentDto addressDto = AddressEnrollmentDto.builder()
                .addressType(addressType)
                .zipCode(zipCode)
                .country(country)
                .address1(address1)
                .address2(address2)
                .build();

        List<AddressEnrollmentDto> addressDtoList = new ArrayList<>();
        addressDtoList.add(addressDto);

        ContactEnrollmentDto contactDto = ContactEnrollmentDto.builder()
                .mobileNumber(mobileNumber)
                .homePhoneNumber(homePhoneNumber)
                .emailAddress(emailAddress)
                .build();

        MemberEnrollmentDto memberDto = MemberEnrollmentDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .contactInfo(contactDto)
                .addressInfo(addressDtoList)
                .build();

        String url = "http://localhost:" + port + "/api/v1/member";

        //when
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(url, memberDto, Object.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        // assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Member> members = memberRepository.findAll();
        List<Address> address = addressRepository.findAll();
        List<Contact> contact = contactRepository.findAll();



        assertThat(members.get(0).getFirstName()).isEqualTo(firstName);
    ///    assertThat(members.get(0).getContact().getEmailAddress()).isEqualTo("jshan88@gmail.com");

        System.out.println(members.get(0).getAddressList().get(1).getAddress1());
        System.out.println(members.get(0).getContact().getEmailAddress());
    }
}
