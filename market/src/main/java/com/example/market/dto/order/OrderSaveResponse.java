package com.example.market.dto.order;

import com.example.market.dto.member.MemberJoinResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class OrderSaveResponse {
    private HttpStatus status;

    private String code;

    private String message;

    private Long orderId;

    public static OrderSaveResponse successResponse(Long orderId) {
        return OrderSaveResponse.builder()
                .status(HttpStatus.OK)
                .code("200")
                .message("주문 성공했습니다")
                .orderId(orderId)
                .build();
    }
}
