package com.example.clonecoding;

import com.example.clonecoding.dto.BannerDto;
import com.example.clonecoding.dto.LectureDto;
import com.example.clonecoding.model.Banner;
import com.example.clonecoding.model.Lecture;
import com.example.clonecoding.repository.BannerRepository;
import com.example.clonecoding.repository.LectureRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class InflearnCrawlingTest {

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private BannerRepository bannerRepository;

    //강의 목록 크롤링시 페이지 순회 인덱스
    private static final int FIRST_PAGE_INDEX = 1;
    private static final int LAST_PAGE_INDEX = 10;

    //강의목록 크롤링
    @Test
    void getHomeList() throws Exception {

        List<LectureDto> list = null;
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
                String[] priceList = originPrice.split(" ");
                String discountPrice = "";
                //price에서 원가부분만 자르기
                if (originPrice.equals("무료")) {
                    originPrice = "무료";
                } else {
                    originPrice = priceList[0];
                    if (priceList.length == 2) {
                        discountPrice = priceList[1];
                    }
                }
                String description = element.getElementsByAttributeValue("class", "course_description").text();
                if (description.length() > 250){
                    description = description.substring(0,250);
                }
                String level = element.getElementsByAttributeValue("class", "course_level").text();
                String skill = element.getElementsByAttributeValue("class", "course_skills").text();

                Lecture lecture = Lecture.builder()
                        .lectureImg(img)
                        .title(title)
                        .instructor(instructor)
                        .originPrice(originPrice)
                        .discountPrice(discountPrice)
                        .description(description)
                        .level(level)
                        .skill(skill)
                        .build();
                lectureRepository.save(lecture);
            }
        }
    }
    // 배너 크롤링
    @Test
    void getbannerList()  throws Exception {

        BannerDto bannerDto = new BannerDto();
        String URL = "https://www.inflearn.com";
        Document doc = null;
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements bannerTable = doc.getElementsByAttributeValue("class", "scene swiper-slide e-marketing-cls");

        String bannerImg; //이미지
        String bannerTitle;//타이틀
        String bannerText; //텍스트

        for (Element element : bannerTable) {
            bannerImg = element.getElementsByAttributeValue("class", "banner-pc-image").attr("src");

            if (bannerImg.length() > 300){
                bannerImg = bannerImg.substring(0,250);
            }
            bannerTitle = element.getElementsByAttributeValue("class", "admin_hero_title title is-3 bold").text();
            bannerText = element.getElementsByAttributeValue("class", "text is-1").text();

            Banner banner = Banner.builder()
                    .bannerImg(bannerImg)
                    .bannerTitle(bannerTitle)
                    .bannerText(bannerText)
                    .build();
            bannerRepository.save(banner);
        }
    }
}