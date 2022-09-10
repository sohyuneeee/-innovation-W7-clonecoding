package com.example.clonecoding.dto;

import com.example.clonecoding.model.Lecture;
import lombok.Getter;



@Getter
public class LectureResponseDto {

    private final Long id;
    private final String imageFile;
    private final String title;
    private final String description;
    private final String lecturer;
    private final int price;
    private final int star;
    private final String reviewCnt;

    public LectureResponseDto(Lecture lecture) {
        this.id = lecture.getId();
        this.title = lecture.getTitle();
        this.lecturer = lecture.getLecturer();
        this.price = lecture.getPrice();
        this.star = lecture.getStar();
        this.description = lecture.getDescription();
        this.imageFile = lecture.getImageFile();
        this.reviewCnt = lecture.getReviewCnt();

    }
}
