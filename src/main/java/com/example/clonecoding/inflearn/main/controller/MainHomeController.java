package com.example.clonecoding.inflearn.main.controller;


import com.example.clonecoding.inflearn.main.service.MainHomeService;
import com.example.clonecoding.inflearn.main.dto.MainBannerDto;
import com.example.clonecoding.inflearn.main.dto.MainLectureDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainHomeController {

    private final MainHomeService mainHomeService;

    @GetMapping("/inflean/home")
    private List<MainLectureDto> getHomeList(HttpServletRequest request){
        return mainHomeService.getHomeList();
    }

    @GetMapping("/inflean/banner")
    private List<MainBannerDto> getbannerList(HttpServletRequest request){
        return mainHomeService.getbannerList();
    }
}
