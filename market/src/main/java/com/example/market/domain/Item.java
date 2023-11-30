package com.example.market.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Item extends BaseDateEntity{
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int stockQuantity;

    public static Item createItem(String name , int stockQuantity) {
        Item item = new Item();
        item.setName(name);
        item.setStockQuantity(stockQuantity);
        return item;
    }



}
