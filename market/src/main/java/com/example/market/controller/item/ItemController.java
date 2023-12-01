package com.example.market.controller.item;

import com.example.market.domain.Item;
import com.example.market.dto.item.ItemListResponse;
import com.example.market.dto.item.ItemSaveRequest;
import com.example.market.dto.item.ItemSaveResponse;
import com.example.market.dto.member.MemberJoinResponse;
import com.example.market.exception.item.FailedItemSaveException;
import com.example.market.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/list")
    public ResponseEntity<List<ItemListResponse>> getItemList() {
        List<Item> items = itemService.findItems();
        return null;
    }

    @PostMapping("/save")
    public ResponseEntity<ItemSaveResponse> saveItem(@RequestBody @Valid ItemSaveRequest dto,BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(ItemSaveResponse.validationErrorResponse(errors),HttpStatus.BAD_REQUEST);
        }
        try {
            ItemSaveResponse response = itemService.saveItem(dto.getName(), dto.getStockQuantity());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new FailedItemSaveException(e);
        }

    }


}
