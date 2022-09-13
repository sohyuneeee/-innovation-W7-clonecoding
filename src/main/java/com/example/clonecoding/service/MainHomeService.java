package com.example.clonecoding.service;

import com.example.clonecoding.dto.MainBannerDto;
import com.example.clonecoding.dto.ResponseDto;
import com.example.clonecoding.model.MainBanner;
import com.example.clonecoding.model.MainLecture;
import com.example.clonecoding.dto.MainLectureDto;
import com.example.clonecoding.repository.MainBannerRepository;
import com.example.clonecoding.repository.MainHomeRepository;
import lombok.RequiredArgsConstructor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MainHomeService {
    private final MainHomeRepository mainHomeRepository;
    private final MainBannerRepository bannerRepository;

    private static final int FIRST_PAGE_INDEX = 1;
    private static final int LAST_PAGE_INDEX = 10;

    public List<MainLectureDto> getHomeList() {
        List<MainLecture> mainLectures = mainHomeRepository.findAll();
        List<MainLectureDto> lectureList = new ArrayList<>();
        for (MainLecture mainLecture : mainLectures) {
            lectureList.add(MainLectureDto.builder()
                    .id(mainLecture.getId())
                    .frontLectureImg(mainLecture.getFrontLectureImg())
                    .frontLectureTitle(mainLecture.getFrontLectureTitle())
                    .frontInstructor(mainLecture.getFrontInstructor())
                    .frontOriginPrice(mainLecture.getFrontOriginPrice())
                    .frontDiscountPrice(mainLecture.getFrontDiscountPrice())
                    .backLectureTitle(mainLecture.getBackLectureTitle())
                    .backDescription(mainLecture.getBackDescription())
                    .backLevel(mainLecture.getBackLevel())
                    .backSkill(mainLecture.getBackSkill())
                    .build());
        }
        return lectureList;
    }

    //메인화면 배너리스트
    public List<MainBannerDto> getbannerList() {
        List<MainBanner> mainbanners = bannerRepository.findAll();
        List<MainBannerDto> bannerList = new ArrayList<>();
        for (MainBanner mainBanner : mainbanners) {
            try {
                bannerList.add(MainBannerDto.builder()
                        .id(mainBanner.getId())
                        .bannerImg(mainBanner.getBannerImg())
                        .bannerTitle(mainBanner.getBannerTitle())
                        .bannerText(mainBanner.getBannerText())
                        .build());
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        return bannerList;
    }
}

