package com.example.market.service.member;
import com.example.market.domain.Cart;
import com.example.market.domain.Member;
import com.example.market.dto.member.MemberJoinRequest;
import com.example.market.dto.member.MemberJoinResponse;
import com.example.market.repository.cart.CartRepository;
import com.example.market.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    //회원 가입
    @Transactional
    public MemberJoinResponse join(MemberJoinRequest dto){

        // 아이디 중복일때, 회원가입 거절
        if (isDuplicateMember(dto.getLoginId())) {

            return MemberJoinResponse.duplicatedIdResponse(dto.getLoginId());
        }

        // 아이디 중복이 아니면, 회원 엔티티 생성 및 저장
        Member member = Member.createMember(dto.getLoginId(), passwordEncoder.encode(dto.getPassword()), dto.getName());

        try {
            Member newMember = memberRepository.save(member);
            cartRepository.save(new Cart(newMember));
            return MemberJoinResponse.successResponse(newMember.getLoginId());
        } catch (DataIntegrityViolationException e) {
            // 중복된 loginId로 회원가입 할때
            return MemberJoinResponse.duplicatedIdResponse(dto.getLoginId());
        }

    }
    // 회원 조회
    public Member findMember(String loginId) {
        return memberRepository.findByLoginId(loginId).orElseThrow();
    }

    // 아이디 중복 체크 메서드
    private boolean isDuplicateMember(String loginId) {

        Optional<Member> member = memberRepository.findByLoginId(loginId);

        return member.isEmpty() ? false : true;

    }

}
