package com.example.market.exception.order;

public class OrderAlreadyCanceledException extends RuntimeException {
    public OrderAlreadyCanceledException() {
        super("해당 주문은 이미 취소 되었습니다");
    }
}
