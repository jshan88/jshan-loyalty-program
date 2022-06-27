package com.loyalty.jshan.web.dto.contact;

import com.loyalty.jshan.domain.contact.Contact;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContactEnrollmentDto {

    private String mobileNumber;
    private String homePhoneNumber;
    private String emailAddress;

    @Builder
    public ContactEnrollmentDto(String mobileNumber, String homePhoneNumber, String emailAddress) {
        this.mobileNumber = mobileNumber;
        this.homePhoneNumber = homePhoneNumber;
        this.emailAddress = emailAddress;
    }

    public Contact toEntity() {
        return Contact.builder()
                .mobileNumber(mobileNumber)
                .homePhoneNumber(homePhoneNumber)
                .emailAddress(emailAddress)
                .build();
    }
}
