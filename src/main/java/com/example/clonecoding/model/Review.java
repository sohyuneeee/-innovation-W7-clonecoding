package com.example.clonecoding.model;

import com.example.clonecoding.dto.request.ReviewRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
public class Review extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int star;

    @JoinColumn(name = "lecture_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Lecture lecture;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Review(ReviewRequestDto requestDto, Member member, Lecture lecture) {
        this.content = requestDto.getContent();
        this.star = requestDto.getStar();
        this.member = member;
        this.lecture = lecture;
    }

    public void update(ReviewRequestDto requestDto) {
        this.content = requestDto.getContent();
        this.star = requestDto.getStar();
    }


}
