package com.example.market.repository.item;

import com.example.market.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select i from Item i where i.id= :id")
    Optional<Item> findByIdWithPessimisticLock(Long id);

}
