package com.example.market.domain;

import com.example.market.exception.cart.InsufficientStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Item extends BaseDateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "ITEM_SEQ_GENERATOR")
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int stock;

    public static Item createItem(String name , int stock) {
        Item item = new Item();
        item.setName(name);
        item.setStock(stock);
        return item;
    }
    //재고 감소
    public void reduceStock(int quantity) {
        int restStock = this.stock - quantity;
        if (restStock < 0) {
            throw new InsufficientStockException();
        }
        this.stock = restStock;
    }



}
