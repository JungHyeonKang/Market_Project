package com.example.market.controller.cart;

import com.example.market.dto.cart.AddToCartRequest;
import com.example.market.dto.cart.AddToCartResponse;
import com.example.market.exception.cart.FailedAddToCartException;
import com.example.market.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<AddToCartResponse> addToCart(@RequestBody AddToCartRequest dto) {
        try {
            AddToCartResponse addToCartResponse = cartService.addToCart(dto.getCartId(), dto.getItemId(), dto.getQuantity());

            return new ResponseEntity<>(addToCartResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new FailedAddToCartException(e);
        }


    }
}
