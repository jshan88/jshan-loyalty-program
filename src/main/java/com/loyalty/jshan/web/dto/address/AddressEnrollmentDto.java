package com.loyalty.jshan.web.dto.address;

import com.loyalty.jshan.domain.address.Address;
import com.loyalty.jshan.domain.address.enumForAddress.AddressType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddressEnrollmentDto {
    
    private AddressType addressType;
    private String zipCode; 
    private String country; 
    private String address1; 
    private String address2;

    @Builder
    public AddressEnrollmentDto(AddressType addressType, String zipCode, String country, String address1, String address2) {
        this.addressType = addressType;
        this.zipCode = zipCode;
        this.country = country; 
        this.address1 = address1; 
        this.address2 = address2; 
    }

    public Address toEntity() {

        return Address.builder()
                .addressType(addressType)
                .zipCode(zipCode)
                .country(country)
                .address1(address1)
                .address2(address2)
                .build();
    }
}
