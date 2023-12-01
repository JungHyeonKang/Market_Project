package com.example.market.exception.cart;

public class FailedAddToCartException extends RuntimeException{

    public FailedAddToCartException(Throwable cause) {
        super("장바구니에 추가 할 수 없습니다.", cause);
    }

    public FailedAddToCartException(String message, Throwable cause) {
        super(message, cause);
    }
}
