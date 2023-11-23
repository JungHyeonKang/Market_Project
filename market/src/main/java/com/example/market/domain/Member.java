package com.example.market.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Member extends BaseDateEntity {

    @GeneratedValue
    @Id
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String password;

    @JoinColumn(name = "cart_id")
    @OneToOne(fetch = LAZY,cascade = CascadeType.ALL)
    private Cart cart;

    public static Member createMember(String loginId , String password){
        Member member = new Member();
        member.setLoginId(loginId);
        member.setPassword(password);
        member.setCart(Cart.createCart());
        return member;
    }

    // member - cart 연관관계 메서드
    private void setCart(Cart cart) {
        this.cart = cart;
        cart.setMember(this);
    }


}
