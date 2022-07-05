package com.loyalty.jshan.order.domain.item;

import com.loyalty.jshan.global.CommonEntity;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public abstract class Item extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private int mileage;

}
