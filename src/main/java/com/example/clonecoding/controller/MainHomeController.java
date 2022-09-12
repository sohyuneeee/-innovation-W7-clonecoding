package com.example.clonecoding.controller;


import com.example.clonecoding.service.MainHomeService;
import com.example.clonecoding.dto.MainBannerDto;
import com.example.clonecoding.dto.MainLectureDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainHomeController {

    private final MainHomeService mainHomeService;

    @GetMapping("/api/lecture")
    private List<MainLectureDto> getHomeList(HttpServletRequest request){
        return mainHomeService.getHomeList();
    }

//    @GetMapping("/api/banner")
//    private List<MainBannerDto> getbannerList(HttpServletRequest request){
//        return mainHomeService.getbannerList();
//    }
}
