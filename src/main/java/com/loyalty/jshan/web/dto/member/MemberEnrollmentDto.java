package com.loyalty.jshan.web.dto.member;

import com.loyalty.jshan.domain.member.Member;
import com.loyalty.jshan.web.dto.contact.ContactEnrollmentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberEnrollmentDto {

    private String firstName;
    private String lastName;
    private ContactEnrollmentDto contactInfo;

    @Builder
    public MemberEnrollmentDto (String firstName, String lastName, ContactEnrollmentDto contactInfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInfo = contactInfo;
    }

    public Member toEntity() {

        return Member.builder()
                .firstName(firstName)
                .lastName(lastName)
                .contact(contactInfo.toEntity())
                .build();
    }
}
