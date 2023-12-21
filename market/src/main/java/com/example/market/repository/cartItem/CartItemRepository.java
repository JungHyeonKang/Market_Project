package com.example.market.repository.cartItem;

import com.example.market.domain.CartItem;
import com.example.market.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    List<CartItem> findAllByCartIdForUpdate(Long cartId);

    @Query("select ci from CartItem ci where ci.id = :id")
    Optional<Item> findItemByCartItemId(Long id);
}
