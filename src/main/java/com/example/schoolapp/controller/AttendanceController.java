package com.example.schoolapp.controller;

import com.example.schoolapp.model.Attendance;
import com.example.schoolapp.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    // ➕ Ajouter une nouvelle présence
    @PostMapping
    public Attendance addAttendance(@RequestBody Attendance attendance) {
        // Vérifie si l'objet student et professor sont fournis
        if (attendance.getStudent() == null || attendance.getProfessor() == null) {
            throw new IllegalArgumentException("Student and Professor must be provided");
        }
        return attendanceRepository.save(attendance);
    }

    // 📋 Récupérer toutes les présences
    @GetMapping
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    // 🔍 Récupérer les présences d’un étudiant par ID
    @GetMapping("/student/{studentId}")
    public List<Attendance> getAttendanceByStudentId(@PathVariable Long studentId) {
        return attendanceRepository.findByStudentStudentId(studentId);
    }

    // 🗑️ Supprimer une présence
    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Long id) {
        attendanceRepository.deleteById(id);
    }
}
