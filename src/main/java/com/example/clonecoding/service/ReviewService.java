package com.example.clonecoding.service;

import com.example.clonecoding.dto.request.ReviewRequestDto;
import com.example.clonecoding.dto.response.ReviewResponseDto;
import com.example.clonecoding.enums.ErrorCode;
import com.example.clonecoding.exception.CustomException;
import com.example.clonecoding.model.*;
import com.example.clonecoding.repository.LectureRepository;
import com.example.clonecoding.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final LectureRepository lectureRepository;

    //전체조회
    public List<ReviewResponseDto> getAllReview() {

        List<Review> reviewList = reviewRepository.findAllByOrderByCreatedAtDesc();

        List<ReviewResponseDto> reviewResponseDtoList = new ArrayList<>();
        for (Review review : reviewList) {
            reviewResponseDtoList.add(new ReviewResponseDto(review));
        }

        return reviewResponseDtoList;
    }

    //작성
    @Transactional
    public ReviewResponseDto create(ReviewRequestDto requestDto) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Member member = userDetails.getMember();

        Lecture lecture = lectureRepository.findByLectureId(requestDto.getLectureId());

        Review review = new Review(requestDto, member, lecture);
        review = reviewRepository.save(review);

        return new ReviewResponseDto(review);





    }


    //수정
    @Transactional
    public ReviewResponseDto update(Long reviewId, ReviewRequestDto requestDto) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(EntityNotFoundException::new);

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Member currentMember = userDetails.getMember();

        if (!Objects.equals(review.getMember().getId(), currentMember.getId())) {
            throw new CustomException(ErrorCode.NOT_SAME_MEMBER);
        }

        review.update(requestDto);
        review = reviewRepository.save(review);

        return new ReviewResponseDto(review);
    }

    public void delete(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(EntityNotFoundException::new);

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Member currentMember = userDetails.getMember();

        if (!Objects.equals(review.getMember().getId(), currentMember.getId())) {
            throw new CustomException(ErrorCode.NOT_SAME_MEMBER);
        }

        reviewRepository.delete(review);
    }




}
