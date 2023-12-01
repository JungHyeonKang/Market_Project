package com.example.market.controller.member;

import com.example.market.dto.member.MemberJoinRequest;
import com.example.market.dto.member.MemberJoinResponse;
import com.example.market.exception.member.FailedJoinException;
import com.example.market.service.member.MemberService;
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
            MemberJoinResponse result = memberService.join(dto);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            throw new FailedJoinException(e);
        }

    }

}
