package com.loyalty.jshan.web.dto.member.address;

import com.loyalty.jshan.domain.member.address.Address;
import com.loyalty.jshan.domain.member.address.enumAddress.AddressType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddressUpdateDto {

    private AddressType addressType;
    private String country;
    private String zipCode;
    private String address1;
    private String address2;

    @Builder
    public AddressUpdateDto(AddressType addressType, String country, String zipCode, String address1, String address2) {
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