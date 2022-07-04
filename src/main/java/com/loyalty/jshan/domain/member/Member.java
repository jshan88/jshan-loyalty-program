package com.loyalty.jshan.domain.member;

import com.loyalty.jshan.domain.CommonEntity;
import com.loyalty.jshan.domain.member.address.Address;
import com.loyalty.jshan.domain.member.contact.Contact;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private LocalDateTime dateOfBirth;

//    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="contactId", referencedColumnName = "id")
    private Contact contact;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addressList = new ArrayList<>();

    @Builder
    public Member (String firstName, String lastName, LocalDateTime dateOfBirth, Contact contact, List<Address> addressList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.contact = contact;
        this.addressList = addressList;
    }

    public void updateMember(String firstName, String lastName, LocalDateTime dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public void mapContactToMember(Contact contact) {
//        contact.updateMember(this);
        this.contact = contact;
        contact.updateMember(this);
    }

    public void mapAddressToMember(Address address) {

        address.updateMember(this);
        this.addressList.add(address);
    }
}
