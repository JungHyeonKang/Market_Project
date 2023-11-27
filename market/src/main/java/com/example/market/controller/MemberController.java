package com.example.market.controller;

import com.example.market.dto.LoginRequest;
import com.example.market.dto.LoginResponse;
import com.example.market.dto.MemberJoinRequest;
import com.example.market.dto.MemberJoinResponse;
import com.example.market.exception.LoginException;
import com.example.market.exception.FailedJoinException;
import com.example.market.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<MemberJoinResponse> MemberRegister(@RequestBody @Valid MemberJoinRequest dto,BindingResult bindingResult){

        // 검증 오류가 있는 경우
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(MemberJoinResponse.validationErrorResponse(errors),HttpStatus.BAD_REQUEST);
        }

        try{
            ResponseEntity<MemberJoinResponse> result = memberService.join(dto);
            return result;
        }catch (Exception e){
            throw new FailedJoinException(e);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest,BindingResult bindingResult) {

        // 검증 오류가 있는 경우
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(LoginResponse.validationErrorResponse(errors),HttpStatus.BAD_REQUEST);
        }

        try {
            ResponseEntity<LoginResponse> result = memberService.login(loginRequest);
            return result;
        }catch (Exception e){
            throw new LoginException(e);
        }


    }


}
