package com.example.market.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseDateEntity {

    @GeneratedValue(strategy = GenerationType.AUTO,generator = "CART_SEQ_GENERATOR")
    @Id
    @Column(name = "cart_id")
    private Long id;

    public static Cart createCart() {
        Cart cart = new Cart();

        return cart;
    }

}
