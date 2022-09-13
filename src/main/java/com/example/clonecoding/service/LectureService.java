package com.example.clonecoding.service;

import com.example.clonecoding.dto.BannerDto;
import com.example.clonecoding.model.Banner;
import com.example.clonecoding.model.Lecture;
import com.example.clonecoding.dto.LectureDto;
import com.example.clonecoding.repository.BannerRepository;
import com.example.clonecoding.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LectureService {
    private final LectureRepository lectureRepository;
    private final BannerRepository bannerRepository;

    //강의목록 리스트
    public List<LectureDto> getHomeList() {
        List<Lecture> lectures = lectureRepository.findAll();
        List<LectureDto> lectureList = new ArrayList<>();
        for (Lecture lecture : lectures) {
            lectureList.add(LectureDto.builder()
                    .id(lecture.getId())
                    .frontLectureImg(lecture.getLectureImg())
                    .frontLectureTitle(lecture.getTitle())
                    .frontInstructor(lecture.getInstructor())
                    .frontOriginPrice(lecture.getOriginPrice())
                    .frontDiscountPrice(lecture.getDiscountPrice())
                    .backLectureTitle(lecture.getTitle())
                    .backDescription(lecture.getDescription())
                    .backLevel(lecture.getLevel())
                    .backSkill(lecture.getSkill())
                    .build());
        }
        return lectureList;
    }

    //배너리스트
    public List<BannerDto> getbannerList() {
        List<Banner> banners = bannerRepository.findAll();
        List<BannerDto> bannerList = new ArrayList<>();
        for (Banner banner : banners) {
            try {
                bannerList.add(BannerDto.builder()
                        .id(banner.getId())
                        .bannerImg(banner.getBannerImg())
                        .bannerTitle(banner.getBannerTitle())
                        .bannerText(banner.getBannerText())
                        .backGroundColor(banner.getBackGroundColor())
                        .build());
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        return bannerList;
    }
}

