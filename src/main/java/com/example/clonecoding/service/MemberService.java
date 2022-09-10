package com.example.clonecoding.service;

import com.example.clonecoding.jwt.TokenDto;
import com.example.clonecoding.dto.request.MemberRequestDto;
import com.example.clonecoding.dto.response.ResponseDto;
import com.example.clonecoding.jwt.TokenProvider;
import com.example.clonecoding.model.Authority;
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

        if(null != memberRepository.findByEmail(requestDto.getEmail())) {
            return ResponseDto.isFail("이미 가입된 이메일 주소 입니다.");
        }

        Member member = Member.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .authority(Authority.ROLE_USER)
                .build();

        memberRepository.save(member);

        return ResponseDto.isSuccess("회원가입 완료");
    }

    public ResponseDto<?> loginMember(MemberRequestDto requestDto, HttpServletResponse response) {
        Member member = isPresentMember(requestDto.getEmail());

        if(null == member) {
            return ResponseDto.isFail("이메일 또는 비밀번호를 확인해주세요.");
        }

        if(!member.validatePassword(passwordEncoder, requestDto.getPassword())) {
            return ResponseDto.isFail("이메일 또는 비밀번호를 확인해주세요.");
        }

        TokenDto tokenDto = tokenProvider.generateTokenDto(member);
        response.addHeader("Authorization", "Bearer " + tokenDto.getAccessToken());
        response.addHeader("Refresh-Token", tokenDto.getRefreshToken());
        response.addHeader("Access-Token-Expire-Time", tokenDto.getAccessTokenExpiresIn().toString());

        Authority authority = member.getAuthority();
        String[] nickname = member.getEmail().split("@");
        return ResponseDto.isSuccess(authority+" / "+nickname[0]);
    }

    public ResponseDto<?> logoutMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return ResponseDto.isFail("Token이 유효하지 않습니다.");
        }
        Member member = tokenProvider.getMemberFromAuthentication();
        if (null == member) {
            return ResponseDto.isFail("사용자를 찾을 수 없습니다.");
        }

        return tokenProvider.deleteRefreshToken(member);
    }

    @Transactional(readOnly = true)
    public Member isPresentMember(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        return optionalMember.orElse(null);
    }
}
