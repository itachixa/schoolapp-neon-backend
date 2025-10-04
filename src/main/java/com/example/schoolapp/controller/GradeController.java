package com.example.schoolapp.controller;

import com.example.schoolapp.model.Grade;
import com.example.schoolapp.repository.GradeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
@CrossOrigin(origins = "*")
public class GradeController {

    private final GradeRepository repo;

    public GradeController(GradeRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/student/{studentId}")
    public List<Grade> getGradesByStudent(@PathVariable Long studentId) {
        return repo.findByStudent_StudentId(studentId);
    }
}
