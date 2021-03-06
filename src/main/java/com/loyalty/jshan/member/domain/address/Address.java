package com.loyalty.jshan.member.domain.address;

import com.loyalty.jshan.global.CommonEntity;
import com.loyalty.jshan.member.domain.address.enums.AddressType;
import com.loyalty.jshan.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Objects;

@DynamicUpdate
@Getter
@NoArgsConstructor
@Entity
public class Address extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private AddressType addressType;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Address address = (Address) o;
//        return getAddressType() == address.getAddressType();
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getAddressType());
//    }

    @Column
    private String country;

    @Column(length = 10)
    private String zipCode;

    @Column
    private String address1;

    @Column
    private String address2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", referencedColumnName = "id")
    private Member member;

    public void mapMemberToAddress(Member member) {
        this.member = member;
//        member.getAddressList().add(this);
        //This is just to add the addressList to the Member Object (Persistence Context)
        //So that they can be retrieved before the flush/commit/clear.

        //to-be added : in case the address
        //if the input address type already exists in the addresslist then update.
        //else insert.
        // (coming to think about it, this logic can only be implemented in the service level,
        // as you would have to .find () the existing addresses of the member first
        // to compare whether the given address type already exists or not.
    }

    @Builder
    public Address (AddressType addressType, String country, String zipCode, String address1, String address2, Member member) {
        this.addressType = addressType;
        this.country = country;
        this.zipCode = zipCode;
        this.address1 = address1;
        this.address2 = address2;
        this.member = member;
    }

    public void updateAddress(AddressType addressType, String country, String zipCode, String address1, String address2) {
        this.addressType = addressType;
        this.country = country;
        this.zipCode = zipCode;
        this.address1 = address1;
        this.address2 = address2;
    } ////////// instead of  if(country != null) this.country = country, for the null parameters,
    ////////// I think I can use @DynamicUpdate, which helps you

}
