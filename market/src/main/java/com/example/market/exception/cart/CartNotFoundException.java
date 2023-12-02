package com.example.market.exception.cart;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException() {
        super("장바구니가 존재하지 않습니다");
    }
}
