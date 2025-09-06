package com.example.schoolapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final com.example.schoolapp.repository.UserRepository repo;
    public UserController(com.example.schoolapp.repository.UserRepository repo) { this.repo = repo; }

    @GetMapping
    public List<com.example.schoolapp.model.User> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<com.example.schoolapp.model.User> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public com.example.schoolapp.model.User create(@RequestBody com.example.schoolapp.model.User body) { return repo.save(body); }

    @PutMapping("/{id}")
    public ResponseEntity<com.example.schoolapp.model.User> update(@PathVariable Long id, @RequestBody com.example.schoolapp.model.User body) {
        return repo.findById(id).map(existing -> {
            // ID preservation
            body.setId(id);
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