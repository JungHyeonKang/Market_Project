package com.example.market.exception.cartitem;

public class CartItemEmptyException extends RuntimeException{
    public CartItemEmptyException() {
        super("장바구니에 상품이 존재하지 않습니다");
    }
}
