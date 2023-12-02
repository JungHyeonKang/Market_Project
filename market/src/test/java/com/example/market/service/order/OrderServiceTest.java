package com.example.market.service.order;

import com.example.market.domain.Cart;
import com.example.market.domain.CartItem;
import com.example.market.domain.Item;
import com.example.market.domain.Member;
import com.example.market.repository.cart.CartRepository;
import com.example.market.repository.cartItem.CartItemRepository;
import com.example.market.repository.item.ItemRepository;
import com.example.market.repository.member.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {
    
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemRepository itemRepository;

//    @BeforeEach
//    public void Dbinit() {
//        Optional<Cart> cart = cartRepository.findById(1L);
//        Optional<Item> item = itemRepository.findById(2L);
//        CartItem.createCartItem(cart.get(), item.get(), 1);
//    }
    @Test
    @Transactional
    public void 회원장바구니상품조회() throws Exception {
        //given
        Optional<Cart> cart = cartRepository.findById(1L);
        System.out.println(cart.get().getId());
       Optional<Item> item = itemRepository.findById(2L);
        System.out.println(item.get().getId());

        CartItem cartItem = CartItem.createCartItem(cart.get(), item.get(), 1);

        CartItem savedCartItem = cartItemRepository.save(cartItem);


        Member member = memberRepository.findById(1L).orElseThrow();
        //when

        //then
        Assertions.assertEquals(savedCartItem.getId(),member.getCart().getCartItems().get(0).getId());
    }


}
