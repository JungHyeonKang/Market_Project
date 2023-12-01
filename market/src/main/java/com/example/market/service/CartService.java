package com.example.market.service;

import com.example.market.domain.Cart;
import com.example.market.domain.CartItem;
import com.example.market.domain.Item;
import com.example.market.dto.cart.AddToCartResponse;
import com.example.market.exception.cart.InsufficientStockException;
import com.example.market.repository.CartRepository;
import com.example.market.repository.cartItem.CartItemRepository;
import com.example.market.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CartService {

    private final CartItemRepository cartItemRepository;

    private final CartRepository cartRepository;

    private final ItemRepository itemRepository;

    @Transactional
    public AddToCartResponse addToCart(Long cartId, Long itemId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NoSuchElementException("장바구니가 존재하지 않습니다."));
        log.info("상품 정보 조회");
        Item item = itemRepository.findByIdWithPessimisticLock(itemId).orElseThrow(() -> new NoSuchElementException("상품이 존재하지 않습니다."));
        log.info("상품 정보 조회 종료 {} " , item.getStock());

        CartItem cartItem = CartItem.createCartItem(cart, item, quantity);

        CartItem addedCartItem = cartItemRepository.save(cartItem);

        return AddToCartResponse.successResponse(addedCartItem.getId());

    }
}
