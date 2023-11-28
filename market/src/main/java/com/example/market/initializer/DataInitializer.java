package com.example.market.initializer;

import com.example.market.domain.Cart;
import com.example.market.domain.Member;
import com.example.market.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        Member member1 = Member.createMember("test1",passwordEncoder.encode("1234"),"테스트유저");

        memberRepository.save(member1);
    }
}
