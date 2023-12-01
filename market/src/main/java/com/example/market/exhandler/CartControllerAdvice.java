package com.example.market.exhandler;

import com.example.market.controller.CartController;
import com.example.market.exception.cart.FailedAddToCartException;
import com.example.market.exception.cart.InsufficientStockException;
import com.example.market.exhandler.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice(assignableTypes = {CartController.class})
public class CartControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInsufficientStockException(InsufficientStockException e) {
        return new ResponseEntity<>(new ErrorResponse("401", e.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(new ErrorResponse("501", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
