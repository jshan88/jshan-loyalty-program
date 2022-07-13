package com.loyalty.jshan.member.dto.address;

import com.loyalty.jshan.member.domain.address.Address;
import com.loyalty.jshan.member.domain.address.enums.AddressType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddressEnrollmentDto {

    private AddressType addressType;
    private String country;
    private String zipCode;
    private String address1;
    private String address2;

    @Builder
    public AddressEnrollmentDto (AddressType addressType, String country, String zipCode
                               ,String address1, String address2)
    {
        this.addressType = addressType;
        this.country = country;
        this.zipCode = zipCode;
        this.address1 = address1;
        this.address2 = address2;
    }

    public Address toEntity() {

        return Address.builder()
                .addressType(this.addressType)
                .country(this.country)
                .zipCode(this.zipCode)
                .address1(this.address1)
                .address2(this.address2)
                .build();
    }
}
