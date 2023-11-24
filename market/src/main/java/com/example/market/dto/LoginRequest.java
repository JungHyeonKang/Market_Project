package com.example.market.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {

    @NotNull
    @Size(min = 1)
    private String loginId;

    @NotNull
    @Size(min = 1)
    private String password;

}
