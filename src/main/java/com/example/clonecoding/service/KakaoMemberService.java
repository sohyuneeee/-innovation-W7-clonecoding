package com.example.clonecoding.service;

import com.example.clonecoding.dto.ResponseDto;
import com.example.clonecoding.dto.request.KakaoMemberRequestDto;
import com.example.clonecoding.dto.response.MemberResponseDto;
import com.example.clonecoding.jwt.SocialTokenDto;
import com.example.clonecoding.jwt.TokenDto;
import com.example.clonecoding.jwt.TokenProvider;
import com.example.clonecoding.model.Authority;
import com.example.clonecoding.model.ErrorCode;
import com.example.clonecoding.model.Member;
import com.example.clonecoding.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Service
public class KakaoMemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Value("${spring.security.oauth2.client.registration.kakao.authorization-grant-type}") String grant_type;
    @Value("${spring.security.oauth2.client.registration.kakao.client-id}") String client_id;
    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}") String redirect_url;
    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}") String token_url;
    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}") String user_info_url;

    public KakaoMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public SocialTokenDto getAccessToken(String code) {

        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", grant_type);
        params.add("client_id", client_id);
        params.add("redirect_url",redirect_url);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> accessTokenResponse = rt.exchange(token_url,
                HttpMethod.POST, kakaoTokenRequest, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        SocialTokenDto tokenDto = null;
        try {
            tokenDto = objectMapper.readValue(accessTokenResponse.getBody(), SocialTokenDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return tokenDto;
    }

    public ResponseDto<?> saveMember(String token, HttpServletResponse response) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token); //(1-4)
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);
        ResponseEntity<String> kakaoProfileResponse = rt.exchange(
                user_info_url,
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        KakaoMemberRequestDto kakaoMemberRequestDto = null;
        try {
            kakaoMemberRequestDto = objectMapper.readValue(kakaoProfileResponse.getBody(), KakaoMemberRequestDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(kakaoMemberRequestDto.getKakao_account().getEmail());
        Member kakaoMember = isPresentMember(kakaoMemberRequestDto.getKakao_account().getEmail());

        if(kakaoMember == null) {
            kakaoMember = Member.builder()
                    .nickname(kakaoMemberRequestDto.getKakao_account().getProfile().getNickname())
                    .email(kakaoMemberRequestDto.getKakao_account().getEmail())
                    .password("default_password")
                    .provider("Kakao")
                    .authority(Authority.ROLE_USER).build();
            memberRepository.save(kakaoMember);
        } else if (!kakaoMember.getProvider().equals("Kakao")) {
            return new ResponseDto<>(null, ErrorCode.USED_EMAIL);
        }


        TokenDto tokenDto = tokenProvider.generateTokenDto(kakaoMember);
        response.addHeader("Authorization", "Bearer " + tokenDto.getAccessToken());
        response.addHeader("Refresh-Token", tokenDto.getRefreshToken());
        response.addHeader("Access-Token-Expire-Time", tokenDto.getAccessTokenExpiresIn().toString());

        MemberResponseDto responseDto = MemberResponseDto.builder()
                .id(kakaoMember.getId())
                .nickname(kakaoMember.getNickname())
                .authority(kakaoMember.getAuthority()).build();

        return new ResponseDto<>(responseDto);
    }

    @Transactional(readOnly = true)
    public Member isPresentMember(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        return optionalMember.orElse(null);
    }
}