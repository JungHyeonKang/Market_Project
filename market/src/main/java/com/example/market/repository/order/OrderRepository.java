package com.example.market.repository.order;

import com.example.market.domain.Member;
import com.example.market.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select o from Order o where o.id = :id")
    public Optional<Member> findOrderByIdWithPessimisticLock(Long id);
}
