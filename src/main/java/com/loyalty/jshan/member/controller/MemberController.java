package com.loyalty.jshan.member.controller;
 
import com.loyalty.jshan.member.dto.MemberUpdateDto;
import com.loyalty.jshan.member.dto.MemberResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.loyalty.jshan.member.service.MemberService;
import com.loyalty.jshan.global.apiResponse.ApiResponseWithSuccess;
import com.loyalty.jshan.member.dto.MemberEnrollmentDto;

@Api("Member APIs")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "Member Enrollment")
    @PostMapping("/api/v1/member")
    public ApiResponseWithSuccess<MemberResponseDto> memberEnrollment(@RequestBody MemberEnrollmentDto requestDto) {

        return ApiResponseWithSuccess.createApiResponse(memberService.enrollMember(requestDto));
    }

    @ApiOperation(value = "Member Retrieval", notes = "Search the member with id.")
    @GetMapping("/api/v1/member/{id}")
    public ApiResponseWithSuccess<MemberResponseDto> memberSearchById(@PathVariable Long id) {

        return ApiResponseWithSuccess.createApiResponse(memberService.searchMember(id));
        // return memberService.searchMember(id);
    }

    @ApiOperation(value = "Member Update", notes = "Update member's profile/addresses/contact information.")
    @PutMapping("/api/v1/member/{id}")
    public ApiResponseWithSuccess<MemberResponseDto> memberUpdate(@PathVariable Long id, @RequestBody MemberUpdateDto requestDto) {

        return ApiResponseWithSuccess.createApiResponse(memberService.updateMember(id, requestDto));
    }
}
