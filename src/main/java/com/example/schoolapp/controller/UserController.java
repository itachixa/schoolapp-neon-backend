package com.example.schoolapp.controller;

import com.example.schoolapp.model.User;
import com.example.schoolapp.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<User> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public User create(@RequestBody User body) {
        return repo.save(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User body) {
        return repo.findById(id).map(existing -> {
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

    // 👇 Endpoint login simple
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User body) {
        return repo.findAll().stream()
                .filter(u -> u.getEmail().equals(body.getEmail()) &&
                             u.getPassword().equals(body.getPassword()) &&
                             u.getRole().equalsIgnoreCase(body.getRole()))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }
}
