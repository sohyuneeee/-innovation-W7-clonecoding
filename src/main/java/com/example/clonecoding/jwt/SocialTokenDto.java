package com.example.clonecoding.jwt;

import lombok.Getter;

@Getter
public class SocialTokenDto {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int refresh_token_expires_in;
}
