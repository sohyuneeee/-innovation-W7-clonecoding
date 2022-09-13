package com.example.clonecoding.controller;

import com.example.clonecoding.dto.ResponseDto;
import com.example.clonecoding.jwt.SocialTokenDto;
import com.example.clonecoding.service.KakaoMemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth2")
@AllArgsConstructor
public class KakaoController {

    private final KakaoMemberService kakaoMemberService;

    @GetMapping("/kakao")
    public ResponseDto<?> getLogin(@RequestParam("code") String code) {
        SocialTokenDto tokenDto = kakaoMemberService.getAccessToken(code);
        return kakaoMemberService.saveMember(tokenDto.getAccess_token());
    }


    //프론트에서 따로 요청을 하는 url을 추가 (토큰발급&헤더추가)

}
