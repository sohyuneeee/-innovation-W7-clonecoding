package com.example.clonecoding.controller;

import com.example.clonecoding.dto.LectureRequestDto;
import com.example.clonecoding.dto.LectureResponseDto;
import com.example.clonecoding.dto.ResponseDto;
import com.example.clonecoding.model.ErrorCode;
import com.example.clonecoding.model.Lecture;
import com.example.clonecoding.service.LectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @GetMapping("api/search")
    public ResponseDto<List<Lecture>> searchLecture(@RequestParam(value = "keyword") String keyword) {
        List<Lecture> lectures;
        try {
            lectures = lectureService.searchLecture(keyword);
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            return new ResponseDto<>(null, ErrorCode.ENTITY_NOT_FOUND);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseDto<>(null,ErrorCode.INVALID_ERROR);
        }

        return new ResponseDto<>(lectures);

    }


//    @PostMapping("api/lecture")
//    public ResponseDto<LectureResponseDto> createLecture(@RequestBody LectureRequestDto requestDto) {
//        LectureResponseDto lectureResponseDto;
//        try {
//            lectureResponseDto = lectureService.createLecture(requestDto);
//        }catch (EntityNotFoundException e){
//            log.error(e.getMessage());
//            return new ResponseDto<>(null, ErrorCode.ENTITY_NOT_FOUND);
//        }catch (Exception e){
//            log.error(e.getMessage());
//            return new ResponseDto<>(null,ErrorCode.INVALID_ERROR);
//        }
//        return new ResponseDto<>(lectureResponseDto);
//    }


}
