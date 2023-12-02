package com.example.market.initializer;

import com.example.market.domain.Item;
import com.example.market.domain.Member;
import com.example.market.repository.item.ItemRepository;
import com.example.market.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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

        Member member2 = Member.createMember("test2",passwordEncoder.encode("1234"),"테스트유저2");
        memberRepository.save(member2);
    }

    private void itemDBInit() {
        Item item1 = Item.createItem("컴퓨터", 1);
        Item item2 = Item.createItem("라면", 1000);
        Item item3 = Item.createItem("옷", 5);
        Item item4 = Item.createItem("양말", 2000);
        Item item5 = Item.createItem("치킨", 1000);
        Item item6 = Item.createItem("아몬드", 500);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
        itemRepository.save(item5);
        itemRepository.save(item6);
    }
}
