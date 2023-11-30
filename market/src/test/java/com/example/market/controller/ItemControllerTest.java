package com.example.market.controller;

import com.example.market.dto.item.ItemSaveRequest;
import com.example.market.dto.item.ItemSaveResponse;
import com.example.market.service.item.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@WebMvcTest
public class ItemControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ItemService itemService;
    
    @Test
    public void 상품등록() throws Exception {
        //given
        Mockito.when(
                itemService.saveItem("test", 100)
        ).thenReturn(
                new ItemSaveResponse(1L, HttpStatus.OK, "200", "하이", null)
        );
        ItemSaveRequest itemSaveRequest = new ItemSaveRequest("test" , 100);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/item/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemSaveRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test"));
        
    }
}
