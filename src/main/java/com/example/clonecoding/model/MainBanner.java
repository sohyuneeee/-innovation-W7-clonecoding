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
public class MainBanner {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    @Column
    private String bannerImg;

    @NotNull
    @Column
    private String bannerTitle;

    @NotNull
    @Column
    private String bannerText;

}
