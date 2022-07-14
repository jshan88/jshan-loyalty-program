package com.loyalty.jshan.member.domain;

import com.loyalty.jshan.global.CommonEntity;
import com.loyalty.jshan.member.domain.address.Address;
import com.loyalty.jshan.member.domain.contact.Contact;
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

    private int remainMileage;

//    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="contactId", referencedColumnName = "id")
    private Contact contact;

    public void mapContactToMember(Contact contact) {
//        contact.updateMember(this);
        this.contact = contact;
        contact.updateMember(this);
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    // cascade : 부모 entity가 persist/remove등 할때 child도 같이
    // orphanRemoval : list에서 remove 되는 address는 삭제처리.
    private List<Address> addressList = new ArrayList<>();

    @Builder
    public Member (String firstName, String lastName, LocalDateTime dateOfBirth,
                   int remainMileage, Contact contact, List<Address> addressList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.remainMileage = remainMileage;
        this.contact = contact;
        this.addressList = addressList;
    }

    public void mapAddressToMember(Address address) {
        address.mapMemberToAddress(this);
        this.addressList.add(address);
    }

    public void updateMember(String firstName, String lastName, LocalDateTime dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public void updateMember(int mileage) {
        this.remainMileage += mileage;
    }
}
