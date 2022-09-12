package com.example.clonecoding.dto.response;

import com.example.clonecoding.model.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberResponseDto {
    private final Long id;
    private final String nickname;
    private final Authority authority;
}
