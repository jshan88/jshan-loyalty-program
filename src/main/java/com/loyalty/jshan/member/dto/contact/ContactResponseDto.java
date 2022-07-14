package com.loyalty.jshan.member.dto.contact;

import com.loyalty.jshan.member.domain.contact.Contact;

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

    @Builder
    public ContactResponseDto(String mobileNumber, String homePhoneNumber, String emailAddress) { 
        this.mobileNumber = mobileNumber; 
        this.homePhoneNumber = homePhoneNumber;
        this.emailAddress = emailAddress;
    }
}
