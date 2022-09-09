package com.example.clonecoding.service;

import com.example.clonecoding.dto.request.MemberRequestDto;
import com.example.clonecoding.model.Member;
import com.example.clonecoding.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

}
