package com.loyalty.jshan.member.dto.address;

import com.loyalty.jshan.member.domain.address.Address;
import com.loyalty.jshan.member.domain.address.enums.AddressType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddressResponseDto {

    private AddressType addressType;
    private String country;
    private String zipCode;
    private String address1;
    private String address2;

    @Builder
    public AddressResponseDto(Address entity) {
        this.addressType = entity.getAddressType();
        this.country = entity.getCountry();
        this.zipCode = entity.getZipCode();
        this.address1 = entity.getAddress1();
        this.address2 = entity.getAddress2();
    }
}
