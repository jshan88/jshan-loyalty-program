package com.loyalty.jshan.service.member;

import com.loyalty.jshan.domain.member.Member;
import com.loyalty.jshan.domain.member.MemberRepository;
import com.loyalty.jshan.web.dto.member.MembersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.loyalty.jshan.web.dto.member.MemberEnrollmentDto;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long memberEnrollment(MemberEnrollmentDto requestDto) {

        return memberRepository.save(requestDto.toEntity()).getId();
    }

    public MembersResponseDto memberSearchById(Long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No member found with the given id : " + id));

        return new MembersResponseDto(member);
    }
}
