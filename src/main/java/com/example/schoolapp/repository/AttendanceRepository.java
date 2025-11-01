package com.example.schoolapp.repository;

import com.example.schoolapp.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudent_StudentIdOrderByDateDesc(Long studentId);
}
