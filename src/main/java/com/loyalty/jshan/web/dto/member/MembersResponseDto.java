package com.loyalty.jshan.web.dto.member;
 
import com.loyalty.jshan.domain.member.Member; 
import com.loyalty.jshan.web.dto.contact.ContactResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MembersResponseDto {

    private String firstName;
    private String lastName;
    private ContactResponseDto contactInfo;

    @Builder
    public MembersResponseDto(Member entity) {
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
//        this.contactInfo = ContactResponseDto.builder()
//                .mobileNumber(entity.getContact().getMobileNumber())
//                .homePhoneNumber(entity.getContact().getHomePhoneNumber())
//                .emailAddress(entity.getContact().getEmailAddress())
//                .build();
    }
}
