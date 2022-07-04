package com.loyalty.jshan.web.dto.member;

import com.loyalty.jshan.web.dto.member.address.AddressUpdateDto;
import com.loyalty.jshan.web.dto.member.contact.ContactUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class MemberUpdateDto {

    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;

    private ContactUpdateDto contactUpdateDto;
    private List<AddressUpdateDto> addressUpdateDtoList = new ArrayList<>();

    @Builder
    public MemberUpdateDto(String firstName, String lastName, LocalDateTime dateOfBirth,
                           ContactUpdateDto contactUpdateDto, List<AddressUpdateDto> addressUpdateDtos) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.contactUpdateDto = contactUpdateDto;
        this.addressUpdateDtoList = addressUpdateDtos;
    }
}
