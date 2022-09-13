package com.example.clonecoding.dto.response;

import com.example.clonecoding.model.Lecture;
import lombok.Getter;



@Getter
public class LectureResponseDto {

    private final Long id;

    private final String lectureImg;
    private final String title;
    private final String description;

    private final String instructor;
    private final String originPrice;

    private final String discountPrice;
    private final int star;

    public LectureResponseDto(Lecture lecture) {
        this.id = lecture.getId();
        this.title = lecture.getTitle();
        this.instructor = lecture.getInstructor();
        this.originPrice = lecture.getOriginPrice();
        this.discountPrice = lecture.getDiscountPrice();
        this.star = lecture.getStar();
        this.description = lecture.getDescription();
        this.lectureImg = lecture.getLectureImg();
    }
}
