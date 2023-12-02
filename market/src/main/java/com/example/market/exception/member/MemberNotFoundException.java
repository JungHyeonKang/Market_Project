package com.example.market.exception.member;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException() {
        super("해당 회원은 존재하지 않습니다");
    }
}
