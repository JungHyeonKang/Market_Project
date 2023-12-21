package com.example.market.service.order;

import com.example.market.domain.*;
import com.example.market.dto.order.OrderCancelResponse;
import com.example.market.dto.order.OrderSaveResponse;
import com.example.market.exception.cart.CartNotFoundException;
import com.example.market.exception.cartitem.CartItemEmptyException;
import com.example.market.exception.member.MemberNotFoundException;
import com.example.market.exception.order.OrderNotFoundException;
import com.example.market.repository.cart.CartRepository;
import com.example.market.repository.cartItem.CartItemRepository;
import com.example.market.repository.item.ItemRepository;
import com.example.market.repository.orderitem.OrderItemRepository;
import com.example.market.repository.member.MemberRepository;
import com.example.market.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    private final OrderItemRepository orderItemRepository;

    private final CartItemRepository cartItemRepository;

    private final CartRepository cartRepository;

    private final ItemRepository itemRepository;

    @Transactional
    public OrderSaveResponse order(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException());
//        //장바구니 조회
//        Cart cart = cartRepository.findById(member.getCart()).orElseThrow(() -> new CartNotFoundException());
        // 해당 회원의 주문할 장바구니 상품 조회
        List<OrderItem> orderItemList = getOrderItems(member);
        // 주문 엔티티 생성
        Order order = Order.createOrder(member, orderItemList);

        // 주문 엔티티 저장
        Order savedOrder = orderRepository.save(order);

        return OrderSaveResponse.successResponse(savedOrder.getId());

    }
    @Transactional
    public OrderCancelResponse cancel(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException());
        // 주문 취소
        order.cancel();

        return OrderCancelResponse.successResponse(order.getId());

    }

    // 해당 회원의 주문할 장바구니 상품 조회 메서드
    private List<OrderItem> getOrderItems(Member member) {
        //장바구니 상품 조회
        List<CartItem> cartItems = member.getCart().getCartItems();

        // 장바구니가 비었다면 에러
        if (cartItems.isEmpty()) {
            throw new CartItemEmptyException();
        }
        // 주문 상품 리스트
        List<OrderItem> orderItemList = new ArrayList<>();
        // 장바구니 아이템을 주문 상품으로 변경
        for (CartItem cartItem : cartItems) {
            //주문상품 생성 및 해당 상품 재고 감소
            Item item = itemRepository.findByIdWithPessimisticLock(cartItem.getItem().getId()).orElseThrow();
            OrderItem orderItem = OrderItem.createOrderItem(item, cartItem.getQuantity());
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }


}
