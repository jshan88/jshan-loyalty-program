package com.loyalty.jshan.redemption.domain.item;
import com.loyalty.jshan.global.CommonEntity;
import com.loyalty.jshan.redemption.domain.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public abstract class Item extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private int mileage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartId", referencedColumnName = "id")
    private Cart cart;

    public void mapItemToCart(Cart cart) {
        this.cart = cart;
    }

//    @Builder
    public Item (String itemName, int mileage, Cart cart) {
        this.itemName = itemName;
        this.mileage = mileage;
        this.cart = cart;
    }

}
