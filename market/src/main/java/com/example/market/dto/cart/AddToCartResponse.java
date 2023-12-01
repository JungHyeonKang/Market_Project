package com.example.market.dto.cart;

import com.example.market.dto.item.ItemSaveResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class AddToCartResponse {
    private Long id;

    private HttpStatus status;

    private String code;

    private String message;

    private List<String> validationErrors;

    public static AddToCartResponse successResponse(Long id) {
        return AddToCartResponse.builder()
                .id(id)
                .status(HttpStatus.OK)
                .code("200")
                .message("장바구니에 추가 되었습니다.")
                .build();
    }


    public static AddToCartResponse validationErrorResponse(List<String> validationErrors) {
        return AddToCartResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .code("400")
                .message("올바른 양식이 아닙니다.")
                .validationErrors(validationErrors)
                .build();
    }
}
