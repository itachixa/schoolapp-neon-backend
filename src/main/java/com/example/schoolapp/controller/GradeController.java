package com.example.schoolapp.controller;

import com.example.schoolapp.model.Grade;
import com.example.schoolapp.repository.GradeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeRepository repo;

    public GradeController(GradeRepository repo) {
        this.repo = repo;
    }

    // 🔹 Récupérer toutes les notes
    @GetMapping
    public List<Grade> getAllGrades() {
        return repo.findAll();
    }

    // 🔹 Récupérer les notes d’un étudiant
    @GetMapping("/student/{studentId}")
    public List<Grade> getGradesByStudent(@PathVariable Long studentId) {
        return repo.findByStudent_StudentId(studentId);
    }

    // 🔹 Enregistrer une nouvelle note
    @PostMapping
    public Grade saveGrade(@RequestBody Grade grade) {
        return repo.save(grade);
    }

    // 🔹 Sauvegarder plusieurs notes d’un coup
    @PostMapping("/bulk")
    public List<Grade> saveAll(@RequestBody List<Grade> grades) {
        return repo.saveAll(grades);
    }

    // 🔹 Mettre à jour une note
    @PutMapping("/{id}")
    public Grade updateGrade(@PathVariable Long id, @RequestBody Grade grade) {
        return repo.findById(id)
                .map(g -> {
                    g.setScore(grade.getScore());
                    g.setCourse(grade.getCourse());
                    g.setProfessor(grade.getProfessor());
                    g.setStudent(grade.getStudent());
                    return repo.save(g);
                })
                .orElseThrow(() -> new RuntimeException("Grade not found with id: " + id));
    }
}
