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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId", referencedColumnName = "id")
    private Member member;

    private LocalDateTime orderedDate;
    private int totalCount;
    private int totalMileage;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // orphan removal?
    private List<Item> itemList = new ArrayList<>();

    public void addItemToCart(Item item) {
        this.itemList.add(item);
        // this.totalCount += 1;
        // this.totalMileage += item.getMileage();
        this.refreshCart();
        item.mapItemToCart(this);
    }

    public void refreshCart() { 
        this.totalCount = 0;
        this.totalMileage = 0;

        itemList.stream().forEach(lst -> {
            this.totalCount += lst.getItemCount();
            this.totalMileage += (lst.getMileage() * lst.getItemCount());
        });
    }

    @Builder
    public Cart(Member member, LocalDateTime orderedDate, int totalCount, int totalMileage, List<Item> itemList) {
        this.member = member;
        this.orderedDate = orderedDate;
        this.totalCount = totalCount;
        this.totalMileage = totalMileage;
        this.itemList = itemList;
    }

    public void updateOrderedDate() {
        this.orderedDate = LocalDateTime.now();
    }

}
