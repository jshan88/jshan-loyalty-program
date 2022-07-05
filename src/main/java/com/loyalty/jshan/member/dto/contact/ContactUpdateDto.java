package com.loyalty.jshan.member.dto.contact;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContactUpdateDto {

    private String mobileNumber;
    private String homePhoneNumber;
    private String emailAddress;

    @Builder
    public ContactUpdateDto(String mobileNumber, String homePhoneNumber, String emailAddress) {
        this.mobileNumber = mobileNumber;
        this.homePhoneNumber = homePhoneNumber;
        this.emailAddress = emailAddress;
    }
}
