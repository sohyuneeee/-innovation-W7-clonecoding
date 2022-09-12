package com.example.clonecoding.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MainLectureDto {
    private String lectureImg;
    private String title;
    private String instructor;
    private String star;
    private String originPrice;
    private String discountPrice;
}
