package com.example.market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class MemberJoinResponse {

    private HttpStatus status;

    private String code;

    private String message;

    private String loginId;

    public static MemberJoinResponse is4xxResponse(String loginId, String message) {
        return MemberJoinResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .code("400")
                .message(message)
                .loginId(loginId)
                .build();
    }

}
