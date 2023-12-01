package com.example.market.exception.item;

public class FailedItemSaveException extends RuntimeException {
    public FailedItemSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedItemSaveException(Throwable cause) {
        super("상품 등록을 실패했습니다",cause);
    }
}
