package com.example.clonecoding.service;

import com.example.clonecoding.dto.LectureRequestDto;
import com.example.clonecoding.dto.LectureResponseDto;
import com.example.clonecoding.model.Lecture;
import com.example.clonecoding.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;


    public List<Lecture> searchLecture(String keyword) {
        List<Lecture> lectures = lectureRepository.findByTitleContaining(keyword);
        return lectures;
    }


//    @Transactional
//    public LectureResponseDto createLecture(LectureRequestDto requestDto) {
//        Lecture lecture = new Lecture(requestDto);
//        lecture = lectureRepository.save(lecture);
//        return new LectureResponseDto(lecture);
//    }
}
