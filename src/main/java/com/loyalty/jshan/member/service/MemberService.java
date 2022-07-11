package com.loyalty.jshan.member.service;

import com.loyalty.jshan.global.exception.ApiRequestException;
import com.loyalty.jshan.member.domain.address.AddressRepository;
import com.loyalty.jshan.member.domain.contact.ContactRepository;
import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.member.repository.MemberRepository;
import com.loyalty.jshan.member.service.address.AddressService;
import com.loyalty.jshan.member.service.contact.ContactService;
import com.loyalty.jshan.member.dto.MemberUpdateDto;
import com.loyalty.jshan.member.dto.MembersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.loyalty.jshan.member.dto.MemberEnrollmentDto;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final ContactRepository contactRepository;
    private final AddressRepository addressRepository;
    private final AddressService addressService;
    private final ContactService contactService;

    @Transactional
    public Long enrollMember(MemberEnrollmentDto requestDto) {

        //Member Creation
        Member member = requestDto.toMemberEntity();
        memberRepository.save(member);

        //Address Information of the member.
        addressService.enrollAddress(member, requestDto.getAddressInfo());

        return member.getId();
    }
    @Transactional
    public MembersResponseDto updateMember(Long id, MemberUpdateDto requestDto) {

        Member member = memberRepository.findById(id)
                .orElseThrow(()->new ApiRequestException("No member found with the given id : " + id));

        member.updateMember(requestDto.getFirstName(), requestDto.getLastName(), requestDto.getDateOfBirth());
        contactService.updateContact(member, requestDto.getContactUpdateDto());
        addressService.updateAddressList(member, requestDto.getAddressUpdateDtoList());

        return new MembersResponseDto(member); // to be checked.
    }
    public MembersResponseDto searchMember(Long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(()->new ApiRequestException("No member found with the given id : " + id));

        return new MembersResponseDto(member);
    }
}
