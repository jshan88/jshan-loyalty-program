package com.loyalty.jshan.service.member.contact;

import com.loyalty.jshan.domain.member.Member;
import com.loyalty.jshan.domain.member.contact.ContactRepository;
import com.loyalty.jshan.web.dto.member.contact.ContactUpdateDto;
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
}
