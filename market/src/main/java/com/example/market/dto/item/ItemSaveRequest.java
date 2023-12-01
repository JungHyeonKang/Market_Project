package com.example.market.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class ItemSaveRequest {

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String name;

    @NotNull
    @Max(1000000000)
    private int stockQuantity;
}
