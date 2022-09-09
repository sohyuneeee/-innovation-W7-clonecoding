package com.example.clonecoding.controller;

import com.example.clonecoding.dto.request.MemberRequestDto;
import com.example.clonecoding.dto.response.ResponseDto;
import com.example.clonecoding.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseDto<?> signup(@RequestBody MemberRequestDto requestDto) {
        return memberService.createMember(requestDto);
    }
}
