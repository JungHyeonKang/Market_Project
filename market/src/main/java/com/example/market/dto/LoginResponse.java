package com.example.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class LoginResponse {

    private HttpStatus status;

    private String code;

    private String message;



}
