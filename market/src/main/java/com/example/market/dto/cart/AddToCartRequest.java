package com.example.market.dto.cart;

import com.example.market.domain.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AddToCartRequest {
    @NotNull
    private Long itemId;
    @NotNull
    private Long cartId;
    @NotNull
    @Min(1)
    private int quantity;
}
