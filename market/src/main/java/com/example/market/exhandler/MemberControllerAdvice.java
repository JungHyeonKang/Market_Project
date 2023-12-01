package com.example.market.exhandler;

import com.example.market.controller.member.MemberController;
import com.example.market.exception.member.FailedJoinException;
import com.example.market.exhandler.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = MemberController.class)
@Slf4j
public class MemberControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMemberJoinException(FailedJoinException e) {
        return new ResponseEntity<>(new ErrorResponse("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
