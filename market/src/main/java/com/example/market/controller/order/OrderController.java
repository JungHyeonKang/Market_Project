package com.example.market.controller.order;

import com.example.market.dto.order.OrderCancelResponse;
import com.example.market.dto.order.OrderSaveResponse;
import com.example.market.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("{cartId}/save")
    public ResponseEntity<OrderSaveResponse> order(@PathVariable Long cartId) {

        OrderSaveResponse response = orderService.order(cartId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("{orderId}/cancel")
    public ResponseEntity<OrderCancelResponse> cancel(@PathVariable Long orderId) {

        OrderCancelResponse response = orderService.cancel(orderId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
