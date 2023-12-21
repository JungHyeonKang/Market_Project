package com.example.market.domain;

import lombok.*;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseDateEntity {

    @GeneratedValue(strategy = GenerationType.AUTO,generator = "CART_SEQ_GENERATOR")
    @Id
    @Column(name = "cart_id")
    private Long id;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    @JoinColumn(name = "member_id")
    @OneToOne(fetch = LAZY)
    private Member member;


    public Cart(Member member) {
        this.member = member;
    }
}
