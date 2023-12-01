package com.example.market.exhandler;

import com.example.market.exception.item.FailedItemSaveException;
import com.example.market.exception.member.FailedJoinException;
import com.example.market.exhandler.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = ItemControllerAdvice.class)
public class ItemControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleFailedItemSaveException(FailedItemSaveException e) {
        System.out.println("하이");
        return new ResponseEntity<>(new ErrorResponse("501", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
