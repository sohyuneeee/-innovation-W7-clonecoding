package com.example.clonecoding.inflearn.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MainBannerDto {
    private String bannerImg;
    private String bannerTitle;
    private String bannerText;
}
