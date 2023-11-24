package com.example.market.member;

import com.example.market.domain.Member;
import com.example.market.dto.MemberJoinRequest;
import com.example.market.dto.MemberJoinResponse;
import com.example.market.repository.MemberRepository;
import com.example.market.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

        member.setLoginId("test1234");
        member.setPassword("1234");
        member.setName("하이");

        MemberJoinRequest member2 = new MemberJoinRequest();
        member2.setLoginId("test1234");
        member2.setPassword("1234");
        member2.setName("하이");
        //when
        ResponseEntity<MemberJoinResponse> case1 = memberService.join(member);

        ResponseEntity<MemberJoinResponse> case2 = memberService.join(member2);

        //then
        // case1은 회원가입 성공 , case2는 아이디 중복으로 회원가입 거절
        assertEquals(case1.getStatusCode(), HttpStatus.OK);
        assertEquals(case2.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 로그인() throws Exception {
        //given

        Member member1 = Member.createMember("test", "1234", "테스트1");

        //when
        Member findMember = memberRepository.save(member1);

        Optional<Member> loginMember = memberRepository.findByLoginIdAndPassword("test", "1234");


        //then

        Assertions.assertEquals(findMember.getLoginId(),loginMember.get().getLoginId());
        Assertions.assertEquals(findMember.getPassword(),loginMember.get().getPassword());

    }
    @Test
    public void 로그인실패() throws Exception {
        //given

        Member member1 = Member.createMember("test", "1234", "테스트1");

        //when
        Member findMember = memberRepository.save(member1);

        Optional<Member> loginMember = memberRepository.findByLoginIdAndPassword("test", "12345");


        //then

        // 아이디와 비밀번호 조회해서 조회되지 않으면 로그인 실패
        Assertions.assertTrue(loginMember.isEmpty());

    }
}
