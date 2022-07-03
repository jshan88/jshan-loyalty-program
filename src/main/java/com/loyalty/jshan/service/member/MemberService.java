package com.loyalty.jshan.service.member;

import com.loyalty.jshan.domain.address.Address;
import com.loyalty.jshan.domain.address.AddressRepository;
import com.loyalty.jshan.domain.contact.Contact;
import com.loyalty.jshan.domain.contact.ContactRepository;
import com.loyalty.jshan.domain.member.Member;
import com.loyalty.jshan.domain.member.MemberRepository;
import com.loyalty.jshan.web.dto.address.AddressEnrollmentDto;
import com.loyalty.jshan.web.dto.contact.ContactEnrollmentDto;
import com.loyalty.jshan.web.dto.member.MembersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.loyalty.jshan.web.dto.member.MemberEnrollmentDto;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final ContactRepository contactRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public Long memberEnrollment(MemberEnrollmentDto requestDto) {

        //Member Creation
        Member member = requestDto.toMemberEntity();
        memberRepository.save(member);

        //Contact Information of the member.
        ContactEnrollmentDto contactDto = requestDto.getContactInfo();
        Contact contact = contactDto.toEntity();
//        contact.updateMember(member);
        member.updateContact(contact);
        contactRepository.save(contact);

        //Address Information of the member.
        List<AddressEnrollmentDto> addressDtos = requestDto.getAddressInfo();
        Address address;
        for (AddressEnrollmentDto addressEnrollmentDto : addressDtos) {
            address = addressEnrollmentDto.toEntity();
//            address.updateMember(member);

            //below is not any related to the DB SQL insert.
            //This is just to add the addressList to the Member Object (Persistence Context)
            //So that they can be retrieved before the flush/commit/clear.
//            member.getAddressList().add(address); /// OneToMany.

            addressRepository.save(address);
            member.addAddress(address);
        }
        return member.getId();
    }

    public MembersResponseDto memberSearchById(Long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No member found with the given id : " + id));

        return new MembersResponseDto(member);
    }
}
