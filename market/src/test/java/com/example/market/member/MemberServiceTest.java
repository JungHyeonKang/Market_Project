package com.example.market.member;

import com.example.market.dto.MemberJoinRequest;
import com.example.market.repository.MemberRepository;
import com.example.market.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void 중복회원가입() throws Exception {
        //given
        MemberJoinRequest member = new MemberJoinRequest();
        member.setLoginId("test");
        member.setPassword("1234");

        MemberJoinRequest member2 = new MemberJoinRequest();
        member2.setLoginId("test");
        member2.setPassword("1234");

        //when
        memberService.join(member);

        memberService.join(member2);

        //then

    }
}
