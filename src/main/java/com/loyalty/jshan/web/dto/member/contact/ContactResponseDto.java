package com.loyalty.jshan.web.dto.member.contact;

import com.loyalty.jshan.domain.member.contact.Contact;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContactResponseDto {

    private String mobileNumber;
    private String homePhoneNumber;
    private String emailAddress;

    public ContactResponseDto(Contact entity) {
        this.mobileNumber = entity.getMobileNumber();
        this.homePhoneNumber = entity.getHomePhoneNumber();
        this.emailAddress = entity.getEmailAddress();
    }
}
