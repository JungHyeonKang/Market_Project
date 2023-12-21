package com.example.market.service.order;

import com.example.market.domain.Cart;
import com.example.market.domain.CartItem;
import com.example.market.domain.Item;
import com.example.market.domain.Member;
import com.example.market.dto.member.MemberJoinRequest;
import com.example.market.dto.order.OrderSaveResponse;
import com.example.market.repository.cart.CartRepository;
import com.example.market.repository.cartItem.CartItemRepository;
import com.example.market.repository.item.ItemRepository;
import com.example.market.repository.member.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    @Autowired
    private OrderService orderService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /*@BeforeEach
    public void Dbinit() {
//        Optional<Cart> cart = cartRepository.findById(1L);
//        Optional<Item> item = itemRepository.findById(2L);
//        CartItem.createCartItem(cart.get(), item.get(), 1);

        Member member1 = Member.createMember("test10",passwordEncoder.encode("1234"),"테스트유저");
        Member member = memberRepository.save(member1);

        Member member2 = Member.createMember("test11",passwordEncoder.encode("1234"),"테스트유저2");
        memberRepository.save(member2);

        Item item1 = Item.createItem("아몬드", 1);

        Item item = itemRepository.save(item1);

        CartItem cartItem1 = CartItem.createCartItem(member.getCart(), item, 1);
        CartItem cartItem2 = CartItem.createCartItem(member2.getCart(), item, 1);


        CartItem savedCartItem1 = cartItemRepository.save(cartItem1);
        CartItem savedCartItem2 = cartItemRepository.save(cartItem2);
    }*/
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

    @Test
    @Transactional
    public void 주문_동시성체크() throws Exception {
        //given


        Optional<Member> member = memberRepository.findById(1L);

        Optional<Member> member2 = memberRepository.findById(2L);

        Optional<Cart> cart1 = cartRepository.findById(1L);
        Optional<Cart> cart2 = cartRepository.findById(2L);
        Optional<Item> item = itemRepository.findById(1L);
//        cart1.get().setId(1L);
//        cart2.get().setId(2L);

        CartItem cartItem1 = CartItem.createCartItem(cart1.get(), item.get(), 1);
        CartItem cartItem2 = CartItem.createCartItem(cart2.get(), item.get(), 1);

        cartItemRepository.saveAndFlush(cartItem1);
        cartItemRepository.saveAndFlush(cartItem2);


        //when
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        executorService.execute(()->{
            OrderSaveResponse order = orderService.order(1L);
            System.out.println("order 1 "+ order.getMessage());
            countDownLatch.countDown();
        });
        executorService.execute(()->{
            OrderSaveResponse order = orderService.order(2L);
            System.out.println("order 2 "+ order.getMessage());
            countDownLatch.countDown();
        });
        countDownLatch.await();
        //the
//        Assertions.assertThrows(FailedJoinException.class,()->{
//
//        });

    }

    @Test
    public void 이중주문() throws Exception {
        //given



        //when
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        executorService.execute(()->{
            OrderSaveResponse order = orderService.order(1L);
            System.out.println("order 1 "+ order.getMessage());
            countDownLatch.countDown();
        });
        executorService.execute(()->{
            OrderSaveResponse order = orderService.order(1L);
            System.out.println("order 2 "+ order.getMessage());
            countDownLatch.countDown();
        });
        countDownLatch.await();
        //then

    }

    @Test
    public void 재고동시성문제() throws Exception {
        //given

        Item item = itemRepository.findByIdWithPessimisticLock(1L).orElseThrow();
        //when
        //when
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        CountDownLatch countDownLatch = new CountDownLatch(2);
//        executorService.execute(()->{
//            Item item = itemRepository.findByIdWithPessimisticLock(1L).orElseThrow();
//             System.out.println("thread 1 "+ item.getName());
//             System.out.println("thread 1 "+ item.getStock());
//            countDownLatch.countDown();
//        });
//        executorService.execute(()->{
//            Item item = itemRepository.findByIdWithPessimisticLock(1L).orElseThrow();
//            System.out.println("thread 2 "+ item.getName());
//            System.out.println("thread 2 "+ item.getStock());
//            countDownLatch.countDown();
//        });
//        countDownLatch.await();

        //then

    }


}
