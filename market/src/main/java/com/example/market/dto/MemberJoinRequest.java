package com.example.market.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MemberJoinRequest {

    @NotNull
    @Size(min = 1,max = 18)
    private String loginId;

    @NotNull
    @Size(min = 1,max = 18)
    private String password;

}
