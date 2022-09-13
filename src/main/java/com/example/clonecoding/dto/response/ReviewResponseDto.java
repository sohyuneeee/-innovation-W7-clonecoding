package com.example.clonecoding.dto.response;

import com.example.clonecoding.model.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {

    private Long id;
    private String content;
    private String nickname;
    private int star;
    private LocalDateTime createdAt;

    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.nickname = review.getNickname();
        this.content= review.getContent();
        this.star = review.getStar();
        this.createdAt = review.getCreatedAt();
    }

}
