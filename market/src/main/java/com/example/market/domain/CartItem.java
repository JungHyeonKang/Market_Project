package com.example.market.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem extends BaseDateEntity {

    @Id
    @GeneratedValue
    @Column(name = "cartItem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;

    private int quantity;

    private String name;

    public static CartItem createCartItem(Cart cart ,Item item , int quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setQuantity(quantity);
        cartItem.setItem(item);
        cartItem.setName(item.getName());
        // 해당 상품 재고 확인 후 객체 생성
        item.checkStock(quantity);
        return cartItem;
    }

    //양뱡향 연관관계 메서드
    public void setCart(Cart cart) {
        this.cart = cart;
        cart.getCartItems().add(this);
    }
}
