package com.example.market.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem extends BaseDateEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ORDER_ITEM_SEQ_GENERATOR")
    @Column(name = "orderItem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int quantity;

    private String name;

    public static OrderItem createOrderItem( Item item, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setName(item.getName());
        orderItem.setQuantity(quantity);
        // 상품 재고감소 이때 락을 걸자
        item.decreaseStock(quantity);
        return orderItem;
    };

    public void recoveryItemStock(int quantity) {

        getItem().increaseStock(quantity);
    }
}
