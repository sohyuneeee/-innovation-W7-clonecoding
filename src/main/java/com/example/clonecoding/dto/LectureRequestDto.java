package com.example.clonecoding.dto;



import lombok.Getter;


@Getter
public class LectureRequestDto {
    private String imageFile;
    private String title;
    private String description;
    private String lecturer;
    private int price;
    private int star;
    private String reviewCnt;

}
