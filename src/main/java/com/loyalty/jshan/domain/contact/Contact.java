package com.loyalty.jshan.domain.contact;

import com.loyalty.jshan.domain.member.Member;
import com.loyalty.jshan.domain.CommonEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Contact extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String mobileNumber;

    @Column(length = 20)
    private String homePhoneNumber;

    @Column
    private String emailAddress; // validation/constraints to be added.

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberId", referencedColumnName = "id")
    private Member member;

    @Builder
    public Contact(String mobileNumber, String homePhoneNumber, String emailAddress, Member member){
        this.mobileNumber = mobileNumber;
        this.homePhoneNumber = homePhoneNumber;
        this.emailAddress = emailAddress;
        this.member = member;
    }

    public void updateMember(Member member) {
        this.member = member;
    }

}
