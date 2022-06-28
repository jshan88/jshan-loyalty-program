package com.loyalty.jshan.domain.address.enumForAddress;
 
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum AddressType {
    H("HOME"), B("BUSINESS");

    private String description;

    
    private AddressType(String description) {
        this.description = description;
    }    
}
