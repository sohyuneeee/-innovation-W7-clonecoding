package com.example.clonecoding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BannerDto {
    private Long id;
    private String bannerImg;
    private String bannerTitle;
    private String bannerText;
}
