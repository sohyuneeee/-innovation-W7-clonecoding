//package com.example.clonecoding.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@NoArgsConstructor
//@Builder
//@AllArgsConstructor
//@Getter
//public class KakaoMember {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="kakaomember_id")
//    private Long id;
//
//    private String kakaoNickname;
//
//    @Column(nullable = false)
//    private String kakaoEmail;
//
//    @Enumerated(EnumType.STRING)
//    private Authority authority;
//}
