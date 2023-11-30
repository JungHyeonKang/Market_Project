package com.example.market.exception.member;

public class FailedJoinException extends RuntimeException{

    public FailedJoinException() {
        super("회원가입을 실패했습니다");
    }

    public FailedJoinException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedJoinException(Throwable cause) {
        super("회원가입을 실패했습니다", cause);
    }
}
