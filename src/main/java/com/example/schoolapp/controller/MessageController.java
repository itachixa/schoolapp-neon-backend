package com.example.schoolapp.controller;

import com.example.schoolapp.model.Message;
import com.example.schoolapp.model.User;
import com.example.schoolapp.repository.MessageRepository;
import com.example.schoolapp.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = {"http://localhost:5173", "https://schoolapp-frontend.vercel.app", "*"})
public class MessageController {

    private final MessageRepository messageRepo;
    private final UserRepository userRepo;

    public MessageController(MessageRepository messageRepo, UserRepository userRepo) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
    }

    // 🔹 Récupérer tous les messages
    @GetMapping
    public List<Message> getAll() {
        return messageRepo.findAll();
    }

    // 🔹 Récupérer une conversation entre deux utilisateurs
    @GetMapping("/conversation/{user1}/{user2}")
    public ResponseEntity<List<Message>> getConversation(
            @PathVariable Long user1,
            @PathVariable Long user2) {

        User u1 = userRepo.findById(user1).orElse(null);
        User u2 = userRepo.findById(user2).orElse(null);
        if (u1 == null || u2 == null)
            return ResponseEntity.badRequest().build();

        List<Message> conv = messageRepo.findConversation(u1, u2);
        return ResponseEntity.ok(conv);
    }

    // 🔹 Envoyer un message
    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Message body) {
        if (body.getSender() == null || body.getReceiver() == null)
            return ResponseEntity.badRequest().build();

        // Sécuriser : s’assurer que sender/receiver existent
        User sender = userRepo.findById(body.getSender().getId()).orElse(null);
        User receiver = userRepo.findById(body.getReceiver().getId()).orElse(null);

        if (sender == null || receiver == null)
            return ResponseEntity.badRequest().build();

        body.setSender(sender);
        body.setReceiver(receiver);
        body.setTimestamp(java.time.LocalDateTime.now());

        Message saved = messageRepo.save(body);
        return ResponseEntity.ok(saved);
    }

    // 🔹 Supprimer un message
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!messageRepo.existsById(id)) return ResponseEntity.notFound().build();
        messageRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
