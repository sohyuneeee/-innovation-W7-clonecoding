package com.example.clonecoding.inflearn.main.repository;

import com.example.clonecoding.inflearn.main.domain.MainLecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainHomeRepository extends JpaRepository<MainLecture, String> {

}
