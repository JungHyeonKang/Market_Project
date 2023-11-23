package com.example.market.service;

import com.example.market.domain.Cart;
import com.example.market.domain.Member;
import com.example.market.dto.MemberJoinRequest;
import com.example.market.dto.MemberJoinResponse;
import com.example.market.repository.CartRepository;
import com.example.market.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    @Transactional
    public ResponseEntity<MemberJoinResponse> join(MemberJoinRequest dto){

        if (isDuplicateMember(dto.getLoginId())) {
            MemberJoinResponse response = MemberJoinResponse.is4xxResponse(dto.getLoginId(), dto.getLoginId() + " 아이디는 이미 존재합니다");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

        Member member = Member.createMember(dto.getLoginId(), dto.getPassword());

        Member newMember = memberRepository.save(member);


        return new ResponseEntity<>(new MemberJoinResponse(HttpStatus.OK, "200", newMember.getLoginId() + "님 회원가입을 축하합니다.", newMember.getLoginId()), HttpStatus.OK);
    }

    @Transactional
    public void delete(Long id) {

        Optional<Member> member = memberRepository.findById(id);

        memberRepository.delete(member.get());

    }

    // 아이디 중복 체크 메서드
    private boolean isDuplicateMember(String loginId) {

        Optional<Member> member = memberRepository.findByLoginId(loginId);

        return member.isEmpty() ? false : true;

    }
}
