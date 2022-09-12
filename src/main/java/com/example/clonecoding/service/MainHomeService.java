package com.example.clonecoding.service;

import com.example.clonecoding.model.MainLecture;
import com.example.clonecoding.dto.MainBannerDto;
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

    //메인화면 강의리스트
    public List<MainLectureDto> getHomeList() {
        String URL = "https://www.inflearn.com";
        Document doc = null;
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements lectureTable = doc.getElementsByAttributeValue("class", "course_card_front");
        List<MainLectureDto> list = new ArrayList<>();

        String img; //이미지
        String title;//타이틀
        String instructor; //강사
        String star; // 별점
        String originPrice; // 원가
        String discountPrice; // 할인가

        for (Element element : lectureTable) {
            img = element.getElementsByAttributeValue("class", "swiper-lazy").attr("src");
            title = element.getElementsByAttributeValue("class", "course_title").text();
            instructor = element.getElementsByAttributeValue("class", "instructor").text();
            star = element.getElementsByAttributeValue("class", "rating").text();

            originPrice = element.getElementsByAttributeValue("class", "price").text();
            //price에서 원가부분만 자르기
            if (originPrice.equals("무료")){
                originPrice = "무료";
            }else {
                originPrice = originPrice.substring(0,6);
            }
            discountPrice = element.getElementsByAttributeValue("class", "pay_price").text();

            try {
                list.add(MainLectureDto.builder()
                        .lectureImg(img)
                        .title(title)
                        .instructor(instructor)
                        .star(star)
                        .originPrice(originPrice)
                        .discountPrice(discountPrice)
                        .build());
                MainLecture mainHome = MainLecture.builder()
                        .lectureImg(img)
                        .title(title)
                        .instructor(instructor)
                        .star(star)
                        .originPrice(originPrice)
                        .discountPrice(discountPrice)
                        .build();
                mainHomeRepository.save(mainHome);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        return list;
    }

    //메인화면 배너리스트
    public List<MainBannerDto> getbannerList() {
        MainBannerDto bannerDto = new MainBannerDto();
        String URL = "https://www.inflearn.com";
        Document doc = null;
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements bannerTable = doc.getElementsByAttributeValue("class","scene swiper-slide e-marketing-cls");
        List<MainBannerDto> bannerList = new ArrayList<>();
        String bannerImg; //이미지
        String bannerTitle;//타이틀
        String bannerText; //텍스트

        for (Element element : bannerTable) {
            bannerImg = element.getElementsByAttributeValue("class", "banner-pc-image").attr("src");
            bannerTitle = element.getElementsByAttributeValue("class", "admin_hero_title title is-3 bold").text();
            bannerText = element.getElementsByAttributeValue("class", "text is-1").text();

            try {
                bannerList.add(MainBannerDto.builder()
                        .bannerImg(bannerImg)
                        .bannerTitle(bannerTitle)
                        .bannerText(bannerText)
                        .build());
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        return bannerList;
    }
}

