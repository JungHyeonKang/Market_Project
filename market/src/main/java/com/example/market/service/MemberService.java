package com.example.market.service;

import com.example.market.domain.Cart;
import com.example.market.domain.Member;
import com.example.market.dto.LoginRequest;
import com.example.market.dto.LoginResponse;
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

        // 아이디 중복일때, 회원가입 거절
        if (isDuplicateMember(dto.getLoginId())) {
            MemberJoinResponse response = MemberJoinResponse.is4xxResponse(dto.getLoginId(), dto.getLoginId() + " 아이디는 이미 존재합니다");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        // 아이디 중복이 아니면, 회원 엔티티 생성 및 저장
        Member member = Member.createMember(dto.getLoginId(), dto.getPassword(),dto.getName());

        Member newMember = memberRepository.save(member);

        return new ResponseEntity<>(new MemberJoinResponse(HttpStatus.OK, "200", newMember.getName() + "님 회원가입을 축하합니다.", newMember.getLoginId()), HttpStatus.OK);
    }

    public ResponseEntity<LoginResponse> login(LoginRequest dto) {
        // 아이디와 비밀번호를 조회
        Optional<Member> findMember = memberRepository.findByLoginIdAndPassword(dto.getLoginId(), dto.getPassword());
        
        // 조회되지 않으면 로그인 실패
        if(findMember.isEmpty()){
            return new ResponseEntity<>(new LoginResponse(HttpStatus.NOT_FOUND, "401", "입력정보를 다시 확인해주세요"),HttpStatus.NOT_FOUND);
        }
        //조회되면 로그인 성공
        return new ResponseEntity<>(new LoginResponse(HttpStatus.OK, "200", "로그인 성공"), HttpStatus.OK);
    }

    @Transactional
    public void delete(Long id) {

        Optional<Member> member = memberRepository.findById(id);

        if (member.isEmpty()) {
            log.info("존재하지 않는 회원입니다.");
        }

        memberRepository.delete(member.get());

    }

    // 아이디 중복 체크 메서드
    private boolean isDuplicateMember(String loginId) {

        Optional<Member> member = memberRepository.findByLoginId(loginId);

        return member.isEmpty() ? false : true;

    }
}
