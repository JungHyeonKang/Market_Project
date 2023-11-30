package com.example.market.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class ItemSaveRequest {

    @NotNull
    @NotEmpty
    @Max(255)
    private String name;

    @NotNull
    @Max(1000000000)
    private int stockQuantity;
}
