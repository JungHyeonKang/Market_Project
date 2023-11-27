package com.example.market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class LoginResponse {

    private HttpStatus status;

    private String code;

    private String message;

    private List<String> validationErrors;
    public static LoginResponse successResponse(String name) {
        return LoginResponse.builder()
                .status(HttpStatus.OK)
                .code("200")
                .message(name + "님 환영합니다")
                .build();
    }

    public static LoginResponse noMatchMemberResponse() {
        return LoginResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .code("404")
                .message("입력정보를 다시 확인 해주세요")
                .build();
    }
    public static LoginResponse validationErrorResponse(List<String> validationErrors) {
        return LoginResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .code("400")
                .message("올바른 양식이 아닙니다.")
                .validationErrors(validationErrors)
                .build();
    }


}
