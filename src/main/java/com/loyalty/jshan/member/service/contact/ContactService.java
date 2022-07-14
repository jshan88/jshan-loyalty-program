package com.loyalty.jshan.member.service.contact;

import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.member.domain.contact.Contact;
import com.loyalty.jshan.member.domain.contact.ContactRepository;
import com.loyalty.jshan.member.dto.contact.ContactResponseDto;
import com.loyalty.jshan.member.dto.contact.ContactUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public void updateContact (Member member, ContactUpdateDto contactUpdateDto) {

        member.getContact().updateContact(contactUpdateDto.getMobileNumber()
                , contactUpdateDto.getHomePhoneNumber()
                , contactUpdateDto.getEmailAddress());
    }

    public ContactResponseDto toContactResponse(Contact contact) { 

        return ContactResponseDto.builder()
                            .mobileNumber(contact.getMobileNumber())
                            .homePhoneNumber(contact.getHomePhoneNumber())
                            .emailAddress(contact.getEmailAddress())
                            .build();
    }
}
