package com.loyalty.jshan.service.member;

import com.loyalty.jshan.domain.member.address.Address;
import com.loyalty.jshan.domain.member.address.AddressRepository;
import com.loyalty.jshan.domain.member.contact.Contact;
import com.loyalty.jshan.domain.member.contact.ContactRepository;
import com.loyalty.jshan.domain.member.Member;
import com.loyalty.jshan.domain.member.MemberRepository;
import com.loyalty.jshan.service.member.address.AddressService;
import com.loyalty.jshan.service.member.contact.ContactService;
import com.loyalty.jshan.web.dto.member.MemberUpdateDto;
import com.loyalty.jshan.web.dto.member.address.AddressEnrollmentDto;
import com.loyalty.jshan.web.dto.member.MembersResponseDto;
import com.loyalty.jshan.web.dto.member.contact.ContactUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.loyalty.jshan.web.dto.member.MemberEnrollmentDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final ContactRepository contactRepository;
    private final AddressRepository addressRepository;

    private final AddressService addressService;
    private final ContactService contactService;

    @Transactional
    public Long memberEnrollment(MemberEnrollmentDto requestDto) {

        //Member Creation
        Member member = requestDto.toMemberEntity();
        memberRepository.save(member);

        //Address Information of the member.
        addressService.mapMemberToAddress(member, requestDto.getAddressInfo());

        return member.getId();
    }

    @Transactional
    public MembersResponseDto memberUpdate(Long id, MemberUpdateDto requestDto) {

        Member member = memberRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No member found with the given id : " + id));

        member.updateMember(requestDto.getFirstName(), requestDto.getLastName(), requestDto.getDateOfBirth());

        contactService.updateContact(member, requestDto.getContactUpdateDto());

        addressService.updateAddressList(member, requestDto.getAddressUpdateDtoList());

        return new MembersResponseDto(member); // to be checked.
    }

    public MembersResponseDto memberSearchById(Long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No member found with the given id : " + id));


        return new MembersResponseDto(member);
    }
}
