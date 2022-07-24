package com.loyalty.jshan.member.service;

import com.loyalty.jshan.global.apiException.ApiErrorCode;
import com.loyalty.jshan.global.apiException.ApiRequestException;
import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.member.repository.MemberRepository;
import com.loyalty.jshan.member.service.address.AddressService;
import com.loyalty.jshan.member.service.contact.ContactService;
import com.loyalty.jshan.member.dto.MemberUpdateDto;
import com.loyalty.jshan.member.dto.address.AddressResponseDto;
import com.loyalty.jshan.member.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.loyalty.jshan.member.dto.MemberEnrollmentDto;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final AddressService addressService;
    private final ContactService contactService;

    @Transactional
    public MemberResponseDto enrollMember(MemberEnrollmentDto requestDto) {

        //Member Creation
        Member member = requestDto.toMemberEntity();
        memberRepository.save(member);

        //Address Information of the member.
        addressService.enrollAddress(member, requestDto.getAddressInfo());

        return toMemberResponse(member); 

    }
    @Transactional
    public MemberResponseDto updateMember(Long id, MemberUpdateDto requestDto) {

        Member member = memberRepository.findById(id)
                .orElseThrow(()->new ApiRequestException(ApiErrorCode.MEMBER_NOT_FOUND));

        member.updateMember(requestDto.getFirstName(), requestDto.getLastName(), requestDto.getDateOfBirth());
        contactService.updateContact(member, requestDto.getContactUpdateDto());
        addressService.updateAddressList(member, requestDto.getAddressUpdateDtoList());

        return toMemberResponse(member);
    }
    public MemberResponseDto searchMember(Long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(()->new ApiRequestException(ApiErrorCode.MEMBER_NOT_FOUND));

        return toMemberResponse(member);
    }

    public MemberResponseDto toMemberResponse(Member member) {  

        return MemberResponseDto.builder()
                    .firstName(member.getFirstName())
                    .lastName(member.getLastName())
                    .dateOfBirth(member.getDateOfBirth())
                    .remainMileage(member.getRemainMileage())
                    .contactResponseDto(contactService.toContactResponse(member.getContact()))
                    .addressResponseDtoList(addressService.toAddressResponseList(member.getAddressList()))
                    .build();
    }

}
