package com.loyalty.jshan.web.dto.contact;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContactResponseDto {

    private String mobileNumber;
    private String homePhoneNumber;
    private String emailAddress;

    @Builder
    public ContactResponseDto(String mobileNumber, String homePhoneNumber, String emailAddress) {
        this.mobileNumber = mobileNumber;
        this.homePhoneNumber = homePhoneNumber;
        this.emailAddress = emailAddress;
    }
}
