package com.example.clonecoding.repository;

import com.example.clonecoding.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
