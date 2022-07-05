package com.loyalty.jshan.member.controller;
 
import com.loyalty.jshan.member.dto.MemberUpdateDto;
import com.loyalty.jshan.member.dto.MembersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.loyalty.jshan.member.service.MemberService;
import com.loyalty.jshan.member.dto.MemberEnrollmentDto;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/v1/member")
    public Long memberEnrollment(@RequestBody MemberEnrollmentDto requestDto) {

        return memberService.enrollMember(requestDto);
    }

    @GetMapping("/api/v1/member/{id}")
    public MembersResponseDto memberSearchByEmail(@PathVariable Long id) {

        return memberService.searchMember(id);
    }

    @PutMapping("/api/v1/member/{id}")
    public MembersResponseDto memberUpdate(@PathVariable Long id, @RequestBody MemberUpdateDto requestDto) {

        return memberService.updateMember(id, requestDto);
    }
}
