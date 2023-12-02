package com.example.market.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order extends BaseDateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ORDER_SEQ_GENERATOR")
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public static Order createOrder(Member member , List<OrderItem> orderItems) {
        Order order = new Order();
        order.setMember(member);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setOrderStatus(OrderStatus.ORDERED);
        return order;
    }

    public void cancel() {
        this.setOrderStatus(OrderStatus.CANCELED);
        for (OrderItem orderItem : this.getOrderItems()) {
            orderItem.recoveryItemStock(orderItem.getQuantity());
        }
    }

    //양방향 연관관계 메서드
    private void addOrderItem(OrderItem orderItem) {
        this.getOrderItems().add(orderItem);
        orderItem.setOrder(this);
    }


}
