package com.loyalty.jshan.web;
 
import com.loyalty.jshan.web.dto.member.MemberUpdateDto;
import com.loyalty.jshan.web.dto.member.MembersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.loyalty.jshan.service.member.MemberService;
import com.loyalty.jshan.web.dto.member.MemberEnrollmentDto;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/v1/member")
    public Long memberEnrollment(@RequestBody MemberEnrollmentDto requestDto) {

        return memberService.memberEnrollment(requestDto);
    }

    @GetMapping("/api/v1/member/{id}")
    public MembersResponseDto memberSearchByEmail(@PathVariable Long id) {

        return memberService.memberSearchById(id);
    }

    @PutMapping("/api/v1/member/{id}")
    public MembersResponseDto memberUpdate(@PathVariable Long id, @RequestBody MemberUpdateDto requestDto) {

        return memberService.memberUpdate(id, requestDto);
    }
}
