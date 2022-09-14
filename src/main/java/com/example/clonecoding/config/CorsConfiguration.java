package com.example.clonecoding.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    // cors 정책에서 벗어나기 위한 구성
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowedOrigins("http://localhost:3000","http://cloneweek-team8.s3-website.ap-northeast-2.amazonaws.com/")
                .exposedHeaders("Authorization","Refresh-Token")
                .allowCredentials(true); // 내 서버가 응답할 때 json을 자바스크립트에서 처리할 수 있게 할지를 설정하는 것. false로 하면 자바스크립트로 요청했을때 오지 않음.
    }
}
