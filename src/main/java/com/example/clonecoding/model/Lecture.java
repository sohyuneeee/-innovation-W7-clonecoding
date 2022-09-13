package com.example.clonecoding.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Lecture {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    @Column
    private String lectureImg;

    @NotNull
    @Column
    private String title;

    @Column
    private int star;

    @NotNull
    @Column
    private String instructor;

    @Column
    private String originPrice;

    @Column
    private String discountPrice;

    @Column
    private String description;

    @Column
    private String level;

    @Column
    private String skill;


}
