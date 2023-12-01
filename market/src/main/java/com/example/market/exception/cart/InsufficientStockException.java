package com.example.market.exception.cart;

public class InsufficientStockException extends RuntimeException{
    public InsufficientStockException() {
        super("상품의 재고가 부족합니다");
    }

    public InsufficientStockException(String message, Throwable cause) {
        super(message, cause);
    }
}
