package com.example.market.exhandler;


import com.example.market.controller.order.OrderController;

import com.example.market.exception.cart.InsufficientStockException;
import com.example.market.exception.cartitem.CartItemEmptyException;
import com.example.market.exception.item.ItemNotFoundException;
import com.example.market.exception.member.MemberNotFoundException;
import com.example.market.exception.order.OrderAlreadyCanceledException;
import com.example.market.exception.order.OrderNotFoundException;
import com.example.market.exhandler.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice(assignableTypes = OrderController.class)
@Slf4j
public class OrderControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleItemNotFoundException(ItemNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse("500",e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMemberNotFoundException(MemberNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse("500",e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInsufficientStockException(InsufficientStockException e) {
        return new ResponseEntity<>(new ErrorResponse("500",e.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleCartItemEmptyException(CartItemEmptyException e) {
        return new ResponseEntity<>(new ErrorResponse("500",e.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse("404",e.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleOrderAlreadyCanceledException(OrderAlreadyCanceledException e) {
        return new ResponseEntity<>(new ErrorResponse("500",e.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(new ErrorResponse("400", "잘못된 요청입니다."), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return new ResponseEntity<>(new ErrorResponse("500", "요청에 실패했습니다. 관리자에게 문의 해주세요."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
