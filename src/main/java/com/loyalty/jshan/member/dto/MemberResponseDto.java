package com.loyalty.jshan.member.dto;
 
import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.member.dto.address.AddressResponseDto;
import com.loyalty.jshan.member.dto.contact.ContactResponseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class MemberResponseDto {

    private String firstName;
    private String lastName;
    private int remainMileage;
    private LocalDateTime dateOfBirth;
    private ContactResponseDto contactResponseDto;
    private List<AddressResponseDto> addressResponseDtoList = new ArrayList<>();
 
    @Builder
    public MemberResponseDto(String firstName, String lastName, int remainMileage, 
                LocalDateTime dateOfBirth, ContactResponseDto contactResponseDto, List<AddressResponseDto> addressResponseDtoList)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.remainMileage = remainMileage;
        this.dateOfBirth = dateOfBirth;
        this.contactResponseDto = contactResponseDto; 
        this.addressResponseDtoList = addressResponseDtoList;
    }
 
}
