package com.example.schoolapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
    private final com.example.schoolapp.repository.ProfessorRepository repo;
    public ProfessorController(com.example.schoolapp.repository.ProfessorRepository repo) { this.repo = repo; }

    @GetMapping
    public List<com.example.schoolapp.model.Professor> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<com.example.schoolapp.model.Professor> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public com.example.schoolapp.model.Professor create(@RequestBody com.example.schoolapp.model.Professor body) { return repo.save(body); }

    @PutMapping("/{id}")
    public ResponseEntity<com.example.schoolapp.model.Professor> update(@PathVariable Long id, @RequestBody com.example.schoolapp.model.Professor body) {
        return repo.findById(id).map(existing -> {
            // ID preservation
            body.setProfessorId(id);
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