package com.loyalty.jshan.web.dto.member;

import com.loyalty.jshan.domain.member.Member;
import com.loyalty.jshan.web.dto.member.address.AddressEnrollmentDto;
import com.loyalty.jshan.web.dto.member.contact.ContactEnrollmentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class MemberEnrollmentDto {

    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;
    private ContactEnrollmentDto contactInfo;
    private List<AddressEnrollmentDto> addressInfo;

    @Builder
    public MemberEnrollmentDto (String firstName, String lastName, LocalDateTime dateOfBirth,
                                ContactEnrollmentDto contactInfo, List<AddressEnrollmentDto> addressInfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.contactInfo = contactInfo;
        this.addressInfo = addressInfo;
    }
    public Member toMemberEntity() {

        return Member.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .dateOfBirth(dateOfBirth)
                    .contact(contactInfo.toEntity())
                    .addressList(new ArrayList<>())
                    .build();
    }
}
