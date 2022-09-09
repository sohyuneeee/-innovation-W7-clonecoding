package com.example.clonecoding.service;

import com.example.clonecoding.dto.request.MemberRequestDto;
import com.example.clonecoding.dto.response.ResponseDto;
import com.example.clonecoding.model.Member;
import com.example.clonecoding.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseDto<?> createMember(MemberRequestDto requestDto) {

        if(null != memberRepository.findByEmail(requestDto.getEmail())) {
            return ResponseDto.isFail("이미 가입된 이메일 주소 입니다.");
        }

        Member member = Member.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();

        memberRepository.save(member);
//        String[] nickname = requestDto.getEmail().split("@");
//        return ResponseDto.isSuccess(nickname[0]);
        return ResponseDto.isSuccess("회원가입 완료");
    }
}
