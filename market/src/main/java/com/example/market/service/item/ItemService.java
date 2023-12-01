package com.example.market.service.item;

import com.example.market.domain.Item;
import com.example.market.dto.item.ItemSaveRequest;
import com.example.market.dto.item.ItemSaveResponse;
import com.example.market.exception.item.FailedItemSaveException;
import com.example.market.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> findItems() {
        List<Item> result = itemRepository.findAll();
        return result;
    }

    @Transactional
    public ItemSaveResponse saveItem(String name, int stockQuantity) {

        Item item = Item.createItem(name, stockQuantity);

        Item savedItem = itemRepository.save(item);

        return ItemSaveResponse.successResponse(savedItem.getId());

    }
}
