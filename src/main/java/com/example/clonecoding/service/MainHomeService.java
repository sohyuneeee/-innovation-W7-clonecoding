package com.example.clonecoding.service;

import com.example.clonecoding.model.MainLecture;
import com.example.clonecoding.dto.MainLectureDto;
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

    private static final int FIRST_PAGE_INDEX = 1;
    private static final int LAST_PAGE_INDEX = 10;

    //메인화면 강의리스트 ( 요청할때마다 돌리는건 비효율적)
    public List<MainLectureDto> getHomeList() {

        List<MainLectureDto> list = null;
        for (int i = FIRST_PAGE_INDEX; i <= LAST_PAGE_INDEX; i++) {
            final String URL = "https://www.inflearn.com/courses?order=seq&page=" + i;
            Document doc = null;
            try {
                doc = Jsoup.connect(URL).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements lectureTable = doc.getElementsByAttributeValue("class", "card course course_card_item");
            list = new ArrayList<>();

            for (Element element : lectureTable) {
                String img = element.getElementsByAttributeValue("class", "swiper-lazy").attr("src");
                String title = element.getElementsByAttributeValue("class", "course_title").text();
                String instructor = element.getElementsByAttributeValue("class", "instructor").text();
                String originPrice = element.getElementsByAttributeValue("class", "price").text();
                //price에서 원가부분만 자르기
                if (originPrice.equals("무료")) {
                    originPrice = "무료";
                } else {
                    originPrice = originPrice.substring(0, 6);
                }
                String discountPrice = element.getElementsByAttributeValue("class", "pay_price").text();
                String description = element.getElementsByAttributeValue("class", "course_description").text();
                String level = element.getElementsByAttributeValue("class", "course_level").text();
                String skill = element.getElementsByAttributeValue("class", "course_skills").text();
                try {
                    list.add(MainLectureDto.builder()
                            .frontLectureImg(img)
                            .frontLectureTitle(title)
                            .frontInstructor(instructor)
                            .frontOriginPrice(originPrice)
                            .frontDiscountPrice(discountPrice)
                            .backLectureTitle(title)
                            .backDescription(description)
                            .backLevel(level)
                            .backSkill(skill)
                            .build());
//                    MainLecture mainHome = MainLecture.builder()
//                            .frontLectureImg(img)
//                            .frontLectureTitle(title)
//                            .frontInstructor(instructor)
//                            .frontOriginPrice(originPrice)
//                            .frontDiscountPrice(discountPrice)
//                            .backLectureTitle(title)
//                            .backDescription(description)
//                            .backLevel(level)
//                            .backSkill(skill)
//                            .build();
//                    mainHomeRepository.save(mainHome);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
        return list;
    }

    //메인화면 배너리스트
//    public List<MainBannerDto> getbannerList() {
//        MainBannerDto bannerDto = new MainBannerDto();
//        String URL = "https://www.inflearn.com";
//        Document doc = null;
//        try {
//            doc = Jsoup.connect(URL).get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Elements bannerTable = doc.getElementsByAttributeValue("class","scene swiper-slide e-marketing-cls");
//        List<MainBannerDto> bannerList = new ArrayList<>();
//        String bannerImg; //이미지
//        String bannerTitle;//타이틀
//        String bannerText; //텍스트
//
//        for (Element element : bannerTable) {
//            bannerImg = element.getElementsByAttributeValue("class", "banner-pc-image").attr("src");
//            bannerTitle = element.getElementsByAttributeValue("class", "admin_hero_title title is-3 bold").text();
//            bannerText = element.getElementsByAttributeValue("class", "text is-1").text();
//
//            try {
//                bannerList.add(MainBannerDto.builder()
//                        .bannerImg(bannerImg)
//                        .bannerTitle(bannerTitle)
//                        .bannerText(bannerText)
//                        .build());
//            } catch (Exception e) {
//                e.getStackTrace();
//            }
//        }
}

