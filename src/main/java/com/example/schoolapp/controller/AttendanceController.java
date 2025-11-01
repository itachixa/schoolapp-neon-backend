package com.example.schoolapp.controller;

import com.example.schoolapp.model.Attendance;
import com.example.schoolapp.repository.AttendanceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = "http://localhost:5173") // adapte selon ton frontend
public class AttendanceController {

    private final AttendanceRepository repo;

    public AttendanceController(AttendanceRepository repo) {
        this.repo = repo;
    }

    // 🔹 Récupérer toutes les présences
    @GetMapping
    public List<Attendance> getAll() {
        return repo.findAll();
    }

    // 🔹 Récupérer les présences d’un étudiant précis
    @GetMapping("/student/{studentId}")
    public List<Attendance> getByStudent(@PathVariable Long studentId) {
        return repo.findByStudent_StudentIdOrderByDateDesc(studentId);
    }

    // 🔹 Sauvegarder une présence
    @PostMapping
    public Attendance save(@RequestBody Attendance attendance) {
        return repo.save(attendance);
    }

    // 🔹 Sauvegarder plusieurs présences à la fois
    @PostMapping("/bulk")
    public List<Attendance> saveAll(@RequestBody List<Attendance> attendances) {
        return repo.saveAll(attendances);
    }
}
