package com.example.clonecoding.controller;

import com.example.clonecoding.dto.ResponseDto;
import com.example.clonecoding.dto.request.ReviewRequestDto;
import com.example.clonecoding.dto.response.ReviewResponseDto;
import com.example.clonecoding.enums.ErrorCode;
import com.example.clonecoding.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //전체조회
    @GetMapping("api/review")
    public ResponseDto<List<ReviewResponseDto>> getAllReview() {
        List<ReviewResponseDto> reviewResponseDtoList;

        try {
            reviewResponseDtoList = reviewService.getAllReview();
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            return new ResponseDto<>(null, ErrorCode.ENTITY_NOT_FOUND);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseDto<>(null,ErrorCode.INVALID_ERROR);
        }

        return new ResponseDto<>(reviewResponseDtoList);
    }



    //작성
    @PostMapping("api/auth/review")
    public ResponseDto<ReviewResponseDto> create(@RequestBody ReviewRequestDto requestDto) {
        ReviewResponseDto reviewResponseDto;

        try {
            reviewResponseDto = reviewService.create(requestDto);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, ErrorCode.ENTITY_NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, ErrorCode.INVALID_ERROR);
        }
        return new ResponseDto<>(reviewResponseDto);
    }

    // 수정
    @PutMapping("api/auth/review/{reviewId}")
    public ResponseDto<ReviewResponseDto> update(@PathVariable Long reviewId, @RequestBody ReviewRequestDto requestDto) {
        ReviewResponseDto reviewResponseDto;

        try {
            reviewResponseDto = reviewService.update(reviewId, requestDto);
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            return new ResponseDto<>(null,ErrorCode.ENTITY_NOT_FOUND);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseDto<>(null,ErrorCode.INVALID_ERROR);
        }
        return new ResponseDto<>(reviewResponseDto);
    }


    //삭제
    @DeleteMapping("api/auth/review/{reviewId}")
    public ResponseDto<String> delete(@PathVariable Long reviewId) {
        try {
            reviewService.delete(reviewId);
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            return new ResponseDto<>(null,ErrorCode.ENTITY_NOT_FOUND);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseDto<>(null,ErrorCode.INVALID_ERROR);
        }
        return new ResponseDto<>("delete success");
    }

}
