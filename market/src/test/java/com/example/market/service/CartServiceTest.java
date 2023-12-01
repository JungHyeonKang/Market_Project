package com.example.market.service;

import com.example.market.domain.Cart;
import com.example.market.domain.Item;
import com.example.market.dto.cart.AddToCartResponse;
import com.example.market.dto.member.MemberJoinRequest;
import com.example.market.repository.CartRepository;
import com.example.market.repository.cartItem.CartItemRepository;
import com.example.market.repository.item.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class CartServiceTest {

    @Autowired
    private CartService cartService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @BeforeEach
    private void DBinIt() {

//        itemRepository.save(Item.createItem("테스트",1));
//        cartRepository.save(Cart.createCart());
//        cartRepository.save(Cart.createCart());
    }

    @Test
    public void 장바구니에추가() throws Exception {

        //given
        Optional<Cart> cart= cartRepository.findById(1L);
        Optional<Item> item = itemRepository.findById(10L);
    
        //when

        AddToCartResponse addToCartResponse = cartService.addToCart(cart.get().getId(), item.get().getId(), 10);
        //then
        Assertions.assertEquals(HttpStatus.OK,addToCartResponse.getStatus());

    }
    @Test
    @DisplayName("상품이 존재하지않을때")
    public void 장바구니실패1() throws Exception {
        //given

        Optional<Cart> cart= cartRepository.findById(1L);
        Optional<Item> item = itemRepository.findById(10L);


        //then
        Assertions.assertThrows(NoSuchElementException.class,()->{
            cartService.addToCart(cart.get().getId(), item.get().getId(), 10);
        });
    }

    @Test
    @DisplayName("동시에 2명의 유저가 재고가 1인 상품을 장바구니에 1개씩 넣었을때  상황")
    @Rollback(value = true)
    @Transactional
    public void 장바구니넣기_동시성체크() throws Exception {
        //given
//        cartRepository.save(Cart.createCart());
//        cartRepository.save(Cart.createCart());
//
//        itemRepository.save(Item.createItem("테스트",1));
        //when
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        executorService.execute(()->{
            cartService.addToCart(1L, 1L, 1);
            countDownLatch.countDown();
        });
        executorService.execute(()->{
            cartService.addToCart(2L, 1L, 1);
            countDownLatch.countDown();
        });
        countDownLatch.await();
        //the
        Assertions.assertTrue(cartItemRepository.findAll().size() == 1);

    }

    @Test
    public void 테스트() throws Exception {
        //given


        //when


        //then

    }
}
