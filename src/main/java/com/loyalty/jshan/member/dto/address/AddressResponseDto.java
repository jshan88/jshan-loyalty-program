package com.loyalty.jshan.member.dto.address;
 
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
    public AddressResponseDto(AddressType addressType, String country, String zipCode, String address1, String address2) {
        this.addressType = addressType;
        this.country = country;
        this.zipCode = zipCode;
        this.address1 = address1;
        this.address2 = address2;
    }
}
