package com.example.clonecoding.repository;

import com.example.clonecoding.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, String> {
    List<Lecture> findByTitleContaining(String keyword);

}
