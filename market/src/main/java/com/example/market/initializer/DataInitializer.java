package com.example.market.initializer;

import com.example.market.domain.Item;
import com.example.market.domain.Member;
import com.example.market.repository.item.ItemRepository;
import com.example.market.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        memberDBInit();
        itemDBInit();
    }

    private void memberDBInit() {
        Member member1 = Member.createMember("test1",passwordEncoder.encode("1234"),"테스트유저");
        memberRepository.save(member1);
    }

    private void itemDBInit() {
        Item item1 = Item.createItem("컴퓨터", 1);
        Item item2 = Item.createItem("라면", 1000);
        Item item3 = Item.createItem("옷", 5);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
    }
}
