package com.example.market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class MemberJoinResponse {

    private HttpStatus status;

    private String code;

    private String message;

    private String loginId;

    private List<String> validationErrors;

    public static MemberJoinResponse successResponse(String loginId) {
        return MemberJoinResponse.builder()
                .status(HttpStatus.OK)
                .code("200")
                .message(loginId + "님 회원가입을 축하합니다.")
                .loginId(loginId)
                .build();
    }

    public static MemberJoinResponse duplicatedIdResponse(String loginId) {
        return MemberJoinResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .code("400")
                .message(loginId + " 아이디는 이미 존재합니다")
                .loginId(loginId)
                .build();
    }

    public static MemberJoinResponse validationErrorResponse(List<String> validationErrors) {
        return MemberJoinResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .code("400")
                .message("올바른 양식이 아닙니다.")
                .validationErrors(validationErrors)
                .build();
    }


}
