package com.example.clonecoding.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MainLectureDto {
    private Long id;
    private String frontLectureImg;
    private String frontLectureTitle;
    private String frontInstructor;
    private String frontOriginPrice;
    private String frontDiscountPrice;
    private String backLectureTitle;
    private String backDescription;
    private String backLevel;
    private String backSkill;
}
