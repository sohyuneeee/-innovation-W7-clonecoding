package com.example.clonecoding.inflearn.main.domain;

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
    private String lectureImg;

    @NotNull
    @Column
    private String title;

    @NotNull
    @Column
    private String instructor;

    @Column
    private String star;

    @Column
    private String originPrice;

    @Column
    private String discountPrice;

}
