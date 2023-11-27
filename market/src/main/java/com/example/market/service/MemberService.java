package com.example.market.service;

import com.example.market.domain.Member;
import com.example.market.dto.LoginRequest;
import com.example.market.dto.LoginResponse;
import com.example.market.dto.MemberJoinRequest;
import com.example.market.dto.MemberJoinResponse;
import com.example.market.exception.FailedJoinException;
import com.example.market.repository.CartRepository;
import com.example.market.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
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

            return new ResponseEntity<>(MemberJoinResponse.duplicatedIdResponse(dto.getLoginId()), HttpStatus.BAD_REQUEST);
        }

        // 아이디 중복이 아니면, 회원 엔티티 생성 및 저장
        Member member = Member.createMember(dto.getLoginId(), dto.getPassword(), dto.getName());

        try {
            Member newMember = memberRepository.save(member);
            return new ResponseEntity<>(MemberJoinResponse.successResponse(newMember.getLoginId()),HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            // 중복된 loginId로 저장하면 에러 발생
            throw new FailedJoinException("중복된 아이디 입니다.",e);
        }


    }

    // 아이디 중복 체크 메서드
    private boolean isDuplicateMember(String loginId) {

        Optional<Member> member = memberRepository.findByLoginId(loginId);

        return member.isEmpty() ? false : true;

    }

    public ResponseEntity<LoginResponse> login(LoginRequest dto) {
        // 아이디와 비밀번호를 조회
        Optional<Member> findMember = memberRepository.findByLoginIdAndPassword(dto.getLoginId(), dto.getPassword());
        
        // 조회되지 않으면 로그인 실패
        if(findMember.isEmpty()){
            return new ResponseEntity<>(LoginResponse.noMatchMemberResponse(),HttpStatus.NOT_FOUND);
        }
        //조회되면 로그인 성공
        return new ResponseEntity<>(LoginResponse.successResponse(findMember.get().getName()), HttpStatus.OK);
    }




}
