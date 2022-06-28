package com.loyalty.jshan.domain.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.loyalty.jshan.domain.CommonEntity;
import com.loyalty.jshan.domain.address.enumForAddress.AddressType;
import com.loyalty.jshan.domain.member.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Address extends CommonEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(length = 1)
    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @Column(length = 10) 
    private String zipCode;

    @Column(length = 30)
    private String country; 

    @Column 
    private String address1;

    @Column 
    private String address2;

    @OneToOne(mappedBy = "address")
    private Member member; 

    @Builder
    public Address (AddressType addressType, String zipCode, String country, String address1, String address2) {
        this.addressType = addressType;
        this.zipCode = zipCode;
        this.country = country;
        this.address1 = address1;
        this.address2 = address2;
    }
}
