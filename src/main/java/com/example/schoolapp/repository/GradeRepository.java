package com.example.schoolapp.repository;

import com.example.schoolapp.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudent_StudentId(Long studentId);
}
