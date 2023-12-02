package com.example.market.exception.order;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException() {
        super("해당 주문은 존재하지 않습니다");
    }
}
