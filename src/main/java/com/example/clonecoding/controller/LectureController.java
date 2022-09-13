package com.example.clonecoding.controller;


import com.example.clonecoding.service.LectureService;
import com.example.clonecoding.dto.BannerDto;
import com.example.clonecoding.dto.LectureDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
}
