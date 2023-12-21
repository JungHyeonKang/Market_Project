package com.example.market.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class MemberJoinRequest {

    @NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문과 숫자만 입력 가능합니다.")
    private String loginId;

    @NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()-_+=<>?]+$", message = "비밀번호는 영문,숫자,특수문자만 입력 가능합니다")
    private String password;

    @NotNull
    @Size(min = 1, max = 25)
    private String name;


}
