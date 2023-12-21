package com.example.market.service.item;

import com.example.market.domain.Item;
import com.example.market.dto.item.ItemSaveResponse;
import com.example.market.repository.item.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
//@Transactional
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("상품이 등록되어야한다")
    public void 상품등록() throws Exception {
        //given

        //when
        ItemSaveResponse response = itemService.saveItem("testItem", 999999999);

        Optional<Item> findItem = itemRepository.findById(response.getId());

        //then
        Assertions.assertEquals("testItem",findItem.get().getName());
    }
}
