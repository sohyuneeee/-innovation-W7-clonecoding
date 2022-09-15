package com.example.clonecoding.controller;


import com.example.clonecoding.dto.ResponseDto;
import com.example.clonecoding.dto.response.LectureResponseDto;
import com.example.clonecoding.enums.ErrorCode;
import com.example.clonecoding.service.LectureService;
import com.example.clonecoding.dto.BannerDto;
import com.example.clonecoding.dto.LectureDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @GetMapping("/api/lecture")
    private List<LectureDto> getHomeList(HttpServletRequest request){
        return lectureService.getHomeList();
    }

    @GetMapping("/api/banner")
    private List<BannerDto> getbannerList(HttpServletRequest request){
        return lectureService.getbannerList();
    }

    @GetMapping("/api/lecture/{lectureId}")
    public ResponseDto<LectureResponseDto> get(@PathVariable Long lectureId){
        LectureResponseDto lectureResponseDto;
        try {
            lectureResponseDto = lectureService.get(lectureId);
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            return new ResponseDto<>(null, ErrorCode.ENTITY_NOT_FOUND);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseDto<>(null,ErrorCode.INVALID_ERROR);
        }

        return new ResponseDto<>(lectureResponseDto);
    }
}
