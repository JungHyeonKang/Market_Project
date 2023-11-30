package com.example.market.dto.item;

import com.example.market.dto.member.MemberJoinResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ItemSaveResponse {
    private Long id;

    private HttpStatus status;

    private String code;

    private String message;

    private List<String> validationErrors;

    public static ItemSaveResponse successResponse(Long id) {
        return ItemSaveResponse.builder()
                .id(id)
                .status(HttpStatus.OK)
                .code("200")
                .message("상품이 등록되었습니다")
                .build();
    }

    public static ItemSaveResponse validationErrorResponse(List<String> validationErrors) {
        return ItemSaveResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .code("400")
                .message("올바른 양식이 아닙니다.")
                .validationErrors(validationErrors)
                .build();
    }
}
