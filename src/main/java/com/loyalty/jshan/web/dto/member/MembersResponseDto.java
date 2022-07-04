package com.loyalty.jshan.web.dto.member;
 
import com.loyalty.jshan.domain.member.Member;
import com.loyalty.jshan.web.dto.member.address.AddressResponseDto;
import com.loyalty.jshan.web.dto.member.contact.ContactResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class MembersResponseDto {

    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;
    private ContactResponseDto contactInfo;
    private List<AddressResponseDto> addressResponseDtoList = new ArrayList<>();

    public MembersResponseDto(Member entity) {
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.dateOfBirth = entity.getDateOfBirth();
        this.contactInfo = new ContactResponseDto(entity.getContact());

        for(int i = 0; i < entity.getAddressList().size(); i++) {
            this.addressResponseDtoList.add(new AddressResponseDto(entity.getAddressList().get(i)));
        }
//        this.addressResponseDtoList = entity.getAddressList().;
//        this.contactInfo = ContactResponseDto.builder()
//                .mobileNumber(entity.getContact().getMobileNumber())
//                .homePhoneNumber(entity.getContact().getHomePhoneNumber())
//                .emailAddress(entity.getContact().getEmailAddress())
//                .build();
    }
}
