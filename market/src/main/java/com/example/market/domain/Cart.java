package com.example.market.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseDateEntity {

    @GeneratedValue(strategy = GenerationType.AUTO,generator = "CART_SEQ_GENERATOR")
    @Id
    @Column(name = "cart_id")
    private Long id;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    public static Cart createCart() {
        Cart cart = new Cart();
        return cart;
    }

}
