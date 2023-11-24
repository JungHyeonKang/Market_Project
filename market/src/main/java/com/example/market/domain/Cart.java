package com.example.market.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Cart {

    @GeneratedValue
    @Id
    @Column(name = "cart_id")
    private Long id;

    public static Cart createCart() {
        Cart cart = new Cart();

        return cart;
    }

}
