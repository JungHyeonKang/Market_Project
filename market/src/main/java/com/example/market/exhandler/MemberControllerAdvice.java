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
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(new ErrorResponse("400", "잘못된 요청입니다."), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return new ResponseEntity<>(new ErrorResponse("500", "요청에 실패했습니다. 관리자에게 문의 해주세요."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
