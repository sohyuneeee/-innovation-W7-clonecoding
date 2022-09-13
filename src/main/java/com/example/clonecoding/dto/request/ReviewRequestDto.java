package com.example.clonecoding.dto.request;

import lombok.Getter;

@Getter
public class ReviewRequestDto {

    private Long lectureId;
    private int star;
    private String content;

}
