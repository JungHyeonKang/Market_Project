package com.example.market.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {

    @NotNull
    @Size(min = 1)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문과 숫자만 입력 가능합니다.")
    private String loginId;

    @NotNull
    @Size(min = 1)
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()-_+=<>?]+$", message = "비밀번호는 영문,숫자,특수문자만 입력 가능합니다")
    private String password;



}
