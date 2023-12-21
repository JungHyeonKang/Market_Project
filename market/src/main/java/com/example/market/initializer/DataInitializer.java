package com.example.market.initializer;

import com.example.market.domain.Cart;
import com.example.market.domain.CartItem;
import com.example.market.domain.Item;
import com.example.market.domain.Member;
import com.example.market.dto.member.MemberJoinRequest;
import com.example.market.repository.cart.CartRepository;
import com.example.market.repository.cartItem.CartItemRepository;
import com.example.market.repository.item.ItemRepository;
import com.example.market.repository.member.MemberRepository;
import com.example.market.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final MemberService memberService;
    private final ItemRepository itemRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public void run(String... args) throws Exception {
        memberDBInit();
        itemDBInit();
       // cartItemDBInit();
    }

    private void memberDBInit() {

        MemberJoinRequest testUser1 = new MemberJoinRequest("test1", passwordEncoder.encode("1234"), "테스트유저");

        MemberJoinRequest testUser2 = new MemberJoinRequest("test2",passwordEncoder.encode("1234"),"테스트유저2");

        memberService.join(testUser1);

        memberService.join(testUser2);

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
    @Transactional
    private void cartItemDBInit() {
        Cart cart1 = cartRepository.findById(1L).orElseThrow();
        Cart cart2 = cartRepository.findById(2L).orElseThrow();
        Item item = itemRepository.findById(1L).orElseThrow();
        CartItem cartItem1 = CartItem.createCartItem(cart1, item, 1);
        CartItem cartItem2 = CartItem.createCartItem(cart2, item, 1);

        cartItemRepository.save(cartItem1);
        cartItemRepository.save(cartItem2);
    }
}
