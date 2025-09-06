package com.example.schoolapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    private final com.example.schoolapp.repository.AttendanceRepository repo;
    public AttendanceController(com.example.schoolapp.repository.AttendanceRepository repo) { this.repo = repo; }

    @GetMapping
    public List<com.example.schoolapp.model.Attendance> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<com.example.schoolapp.model.Attendance> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public com.example.schoolapp.model.Attendance create(@RequestBody com.example.schoolapp.model.Attendance body) { return repo.save(body); }

    @PutMapping("/{id}")
    public ResponseEntity<com.example.schoolapp.model.Attendance> update(@PathVariable Long id, @RequestBody com.example.schoolapp.model.Attendance body) {
        return repo.findById(id).map(existing -> {
            // ID preservation
            body.setAttendanceId(id);
            return ResponseEntity.ok(repo.save(body));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}