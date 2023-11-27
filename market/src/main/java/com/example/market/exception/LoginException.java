package com.example.market.exception;

public class LoginException extends RuntimeException{
    public LoginException(Throwable cause) {
        super("로그인 실패", cause);
    }
}
