package com.example.clonecoding.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GoogleController {

    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    // OAuth2 로그인 Authentication 작동 원리
    @GetMapping("/test/oauth/login")
    public @ResponseBody String loginOAuthTest(Authentication authentication, @AuthenticationPrincipal OAuth2User oauth){ //DI(의존성주입)
        System.out.println("/test/oauth/login ================");
        // ClassCastException해서 오류가 안난다
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("authentication = " + oauth2User.getAttributes());
        //Auth2User.getAttributes()의 정보는 PrincipalOauthUserSerivce파일의 super.loadUser(userRequest).getAttributes()의 정보와 동일
        System.out.println("oAuth2User = " + oauth.getAttributes());
        return "OAuth 세션정보확인";
    }
}
