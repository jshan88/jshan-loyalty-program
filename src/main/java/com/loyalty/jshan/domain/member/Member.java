package com.loyalty.jshan.domain.member;

import com.loyalty.jshan.domain.CommonEntity;
import com.loyalty.jshan.domain.address.Address;
import com.loyalty.jshan.domain.address.enumAddress.AddressType;
import com.loyalty.jshan.domain.contact.Contact;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String firstName;

    @Column(length = 30, nullable = false)
    private String lastName;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Contact contact;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addressList = new ArrayList<>();

    @Builder
    public Member (String firstName, String lastName, Contact contact, List<Address> addressList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.addressList = addressList;
    }

    public void updateContact(Contact contact) {
        contact.updateMember(this);
        this.contact = contact;
    }

    public void addAddress(Address address) {

        address.updateMember(this);
        this.addressList.add(address);
    }
}
