package com.example.clonecoding.inflearn;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class MainApp {
    public static void main(String[] args) {

//        String URL = "https://www.inflearn.com";
//        Document doc = null;
//        try {
//            doc = Jsoup.connect(URL).get();
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
//
//        //강의목록으로 나오는 태그를 찾아서 가져오도록 한다.
//        //1. 제목을 가져온다.
//        Elements titleElements = doc.select("div.card-content > div.course_title");
//        for (int j = 0; j < titleElements.size(); j++) {
//            final String title = titleElements.get(j).text();
//            System.out.println("강의 제목: " + title);
//        }
//
//        //2. 강사를 가져온다
//        Elements instructorElements = doc.select("div.card-content > div.instructor");
//        for (int j = 0; j < instructorElements.size(); j++) {
//            final String instructor = instructorElements.get(j).text();
//            System.out.println("강사: " + instructor);
//        }
//
//        //3. 별점을 가져온다
//        Elements ratingElements = doc.select("div.card-content > div.rating");
//        for (int j = 0; j < ratingElements.size(); j++) {
//            final String rating = ratingElements.get(j).text();
//            System.out.println("별점 : " + rating);
//        }
//
//        //4. 가격을 가져온다(원가 + 할인가)
//        Elements priceElements = doc.select("div.card-content > div.price");
//        for (int j = 0; j < priceElements.size(); j++) {
//            final String price = priceElements.get(j).text();
//            System.out.println("원가 + 할인가 : " + price);
//        }
//
//        //5. 이미지를 가져온다
        try {
            Elements imageUrlElements = doc.getElementsByClass("swiper-lazy");
        }
//
//        for (Element element : imageUrlElements) {
//            //절대 경로 URL을 추출하고 싶으면 속성 값 prefix로  abs: 를 붙이면 된다
//            System.out.println("imgUrl : " +  element.attr("abs: src"));
//        }
        final String inflearnUrl = "https://www.inflearn.com";
        Connection conn = Jsoup.connect(inflearnUrl);

        try {
            Document document = conn.get();
            Elements imageUrlElements = document.getElementsByClass("swiper-lazy");

            for (Element element : imageUrlElements) {
                System.out.println("imgUrl : " + element.attr("abs:src"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
