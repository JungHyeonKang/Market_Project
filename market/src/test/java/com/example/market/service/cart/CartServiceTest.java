package com.example.market.service.cart;

import com.example.market.domain.Cart;
import com.example.market.domain.Item;
import com.example.market.dto.cart.AddToCartResponse;
import com.example.market.exception.cart.InsufficientStockException;
import com.example.market.repository.cart.CartRepository;
import com.example.market.repository.cartItem.CartItemRepository;
import com.example.market.repository.item.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;
import java.util.Optional;

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
        Optional<Item> item = itemRepository.findById(1L);
    
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
    @DisplayName("상품 재고가 부족할때")
    public void 장바구니실패2() throws Exception {
        //given

        Optional<Cart> cart= cartRepository.findById(1L);
        Optional<Item> item = itemRepository.findById(1L);


        //then
        Assertions.assertThrows(InsufficientStockException.class,()->{
            cartService.addToCart(cart.get().getId(), item.get().getId(), 10);
        });
    }


}
