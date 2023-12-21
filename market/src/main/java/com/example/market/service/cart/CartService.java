package com.example.market.service.cart;

import com.example.market.domain.Cart;
import com.example.market.domain.CartItem;
import com.example.market.domain.Item;
import com.example.market.dto.cart.AddToCartResponse;
import com.example.market.exception.cart.CartNotFoundException;
import com.example.market.exception.item.ItemNotFoundException;
import com.example.market.repository.cart.CartRepository;
import com.example.market.repository.cartItem.CartItemRepository;
import com.example.market.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException());

        Item item = itemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException());

        CartItem cartItem = CartItem.createCartItem(cart, item, quantity);

        CartItem addedCartItem = cartItemRepository.save(cartItem);

        return AddToCartResponse.successResponse(addedCartItem.getId());

    }

}
