package com.example.market.controller;

import com.example.market.dto.MemberJoinRequest;
import com.example.market.dto.MemberJoinResponse;
import com.example.market.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.http.HttpResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<MemberJoinResponse> MemberRegister(@RequestBody @Valid MemberJoinRequest dto){

        ResponseEntity<MemberJoinResponse> result = memberService.join(dto);

        return result;
    }

    @PostMapping("/delete/{id}")
    public void MemberDelete(@PathVariable Long id){
        memberService.delete(id);

    }

}
