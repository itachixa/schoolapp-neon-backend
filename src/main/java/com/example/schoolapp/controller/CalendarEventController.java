package com.example.schoolapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/calendar")
public class CalendarEventController {
    private final com.example.schoolapp.repository.CalendarEventRepository repo;
    public CalendarEventController(com.example.schoolapp.repository.CalendarEventRepository repo) { this.repo = repo; }

    @GetMapping
    public List<com.example.schoolapp.model.CalendarEvent> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<com.example.schoolapp.model.CalendarEvent> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public com.example.schoolapp.model.CalendarEvent create(@RequestBody com.example.schoolapp.model.CalendarEvent body) { return repo.save(body); }

    @PutMapping("/{id}")
    public ResponseEntity<com.example.schoolapp.model.CalendarEvent> update(@PathVariable Long id, @RequestBody com.example.schoolapp.model.CalendarEvent body) {
        return repo.findById(id).map(existing -> {
            // ID preservation
            body.setEventId(id);
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