package com.example.market.dto.order;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class OrderCancelResponse {
    private HttpStatus status;

    private String code;

    private String message;

    private Long orderId;

    public static OrderCancelResponse successResponse(Long orderId) {
        return OrderCancelResponse.builder()
                .status(HttpStatus.OK)
                .code("200")
                .message("주문을 취소 했습니다")
                .orderId(orderId)
                .build();
    }
}
