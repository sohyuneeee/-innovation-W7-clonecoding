package com.example.clonecoding.controller;

import com.example.clonecoding.dto.ResponseDto;
import com.example.clonecoding.dto.request.MemberRequestDto;

import com.example.clonecoding.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/api/member/signup")
    public ResponseDto<?> signup(@RequestBody MemberRequestDto requestDto) {
        return memberService.createMember(requestDto);
    }

    @PostMapping("/api/member/login")
    public ResponseDto<?> login(@RequestBody MemberRequestDto requestDto, HttpServletResponse response) {
        return memberService.loginMember(requestDto, response);
    }

    @PostMapping("/api/auth/member/logout")
    public ResponseDto<?> logout(HttpServletRequest request) {
        return  memberService.logoutMember(request);
    }
}
