package com.example.market.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Member extends BaseDateEntity {

    @GeneratedValue(strategy = GenerationType.AUTO,generator = "MEMBER_SEQ_GENERATOR")
    @Id
    @Column(name = "member_id")
    private Long id;

    @Column(length = 25,unique = true,nullable = false)
    private String loginId;

    @Column(length = 255,nullable = false)
    private String password;

    @Column(length = 25,nullable = false,columnDefinition = "varchar(25) CHARACTER SET utf8mb4")
    private String name;

    @Column(length = 25,nullable = false)
    private String role;

    @OneToOne(mappedBy = "member" , cascade = CascadeType.ALL)
    private Cart cart;
    public static Member createMember(String loginId , String password,String name){
        Member member = new Member();
        member.setLoginId(loginId);
        member.setPassword(password);
        member.setName(name);
        member.setRole("ROLE_ADMIN");
        return member;
    }




}
