package com.loyalty.jshan.redemption.domain;

import com.loyalty.jshan.global.CommonEntity;
import com.loyalty.jshan.member.domain.Member;
import com.loyalty.jshan.redemption.domain.item.Item;
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
public class Cart extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="memberId", referencedColumnName = "id")
    private Member member;

    private LocalDateTime expiryDate;

    private int totalCount;

    private int totalMileage;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> itemList = new ArrayList<>();

    public void addItemToCart(Item item) {
        this.itemList.add(item);
        this.totalCount += 1;
        this.totalMileage += item.getMileage();
        item.mapItemToCart(this);
    }

    @Builder
    public Cart(Member member, LocalDateTime expiryDate, int totalCount, int totalMileage, List<Item> itemList) {
        this.member = member;
        this.expiryDate = expiryDate;
        this.totalCount = totalCount;
        this.totalMileage = totalMileage;
        this.itemList = itemList;
    }

}
