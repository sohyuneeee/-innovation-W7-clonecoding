package com.example.clonecoding.repository;

import com.example.clonecoding.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LectureRepository extends JpaRepository <Lecture, Long> {
    @Query("select l from Lecture l where l.id=:id")
    Lecture findByLectureId(Long id);

    List<Lecture> findByTitleContaining(String keyword);
}
