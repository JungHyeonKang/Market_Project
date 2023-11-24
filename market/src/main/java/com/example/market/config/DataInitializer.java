package com.example.market.config;

import com.example.market.domain.Cart;
import com.example.market.domain.Member;
import com.example.market.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final MemberRepository memberRepository;

    @Override
    public void run(String... args) throws Exception {

        Member member1 = Member.createMember("test1", "1234","test1");

        memberRepository.save(member1);
    }
}
