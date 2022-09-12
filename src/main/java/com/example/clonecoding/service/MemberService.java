package com.example.clonecoding.service;

import com.example.clonecoding.dto.ResponseDto;
import com.example.clonecoding.dto.response.MemberResponseDto;
import com.example.clonecoding.jwt.TokenDto;
import com.example.clonecoding.dto.request.MemberRequestDto;

import com.example.clonecoding.jwt.TokenProvider;
import com.example.clonecoding.model.Authority;
import com.example.clonecoding.model.ErrorCode;
import com.example.clonecoding.model.Member;
import com.example.clonecoding.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public ResponseDto<?> createMember(MemberRequestDto requestDto) {

        if(!memberRepository.findByEmail(requestDto.getEmail()).isEmpty()) {
            return new ResponseDto<>(null, ErrorCode.DUPLICATED_EMAIL);
        }
        String[] nickname = requestDto.getEmail().split("@");

        Member member = Member.builder()
                .email(requestDto.getEmail())
                .nickname(nickname[0])
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .authority(Authority.ROLE_USER)
                .build();

        memberRepository.save(member);

        return new ResponseDto<>("회원가입 완료");
    }

    public ResponseDto<?> loginMember(MemberRequestDto requestDto, HttpServletResponse response) {
        Member member = isPresentMember(requestDto.getEmail());

        if(null == member) {
            return new ResponseDto<>(null,ErrorCode.EMAIL_NOT_FOUND);
        }

        if(!member.validatePassword(passwordEncoder, requestDto.getPassword())) {
            return new ResponseDto<>(null,ErrorCode.PASSWORDS_NOT_MATCHED);
        }

        TokenDto tokenDto = tokenProvider.generateTokenDto(member);
        response.addHeader("Authorization", "Bearer " + tokenDto.getAccessToken());
        response.addHeader("Refresh-Token", tokenDto.getRefreshToken());
        response.addHeader("Access-Token-Expire-Time", tokenDto.getAccessTokenExpiresIn().toString());

        MemberResponseDto responseDto = MemberResponseDto.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .authority(member.getAuthority()).build();

        return new ResponseDto<>(responseDto);
    }

    public ResponseDto<?> logoutMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return new ResponseDto<>(null, ErrorCode.BAD_TOKEN_REQUEST);
        }
        Member member = tokenProvider.getMemberFromAuthentication();
        if (null == member) {
            return new ResponseDto<>(null, ErrorCode.MEMBER_NOT_FOUND);
        }

        return tokenProvider.deleteRefreshToken(member);
    }

    @Transactional(readOnly = true)
    public Member isPresentMember(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        return optionalMember.orElse(null);
    }
}
