package com.example.clonecoding.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MainLecture {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    @Column
    private String frontLectureImg;

    @NotNull
    @Column
    private String frontLectureTitle;

    @NotNull
    @Column
    private String frontInstructor;

    @Column
    private String frontOriginPrice;

    @Column
    private String frontDiscountPrice;

    @Column
    private String backLectureTitle;

    @Column
    private String backDescription;

    @Column
    private String backLevel;

    @Column
    private String backSkill;


}
