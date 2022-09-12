package com.example.clonecoding.model;

import com.example.clonecoding.dto.LectureRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String lecturer;

    private int price;

    private String imageFile;

    private String description;

    private int star;

    private String reviewCnt;

    public Lecture(LectureRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.lecturer = requestDto.getLecturer();
        this.price = requestDto.getPrice();
        this.star = requestDto.getStar();
        this.description = requestDto.getDescription();
        this.imageFile = requestDto.getImageFile();
        this.reviewCnt = requestDto.getReviewCnt();
    }


}
