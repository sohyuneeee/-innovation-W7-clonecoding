package com.example.clonecoding.repository;

import com.example.clonecoding.model.MainLecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainHomeRepository extends JpaRepository<MainLecture, String> {

}
