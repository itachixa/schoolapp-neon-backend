package com.example.schoolapp.controller;

import com.example.schoolapp.model.Message;
import com.example.schoolapp.model.User;
import com.example.schoolapp.repository.MessageRepository;
import com.example.schoolapp.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageController(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Message> all() {
        return messageRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Message body) {
        if (body.getSender() == null || body.getSender().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        // 🧠 Vérifie que le sender existe
        User sender = userRepository.findById(body.getSender().getId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(body.getReceiver()); // null si chat général
        message.setContent(body.getContent());
        message.setTimestamp(LocalDateTime.now());

        return ResponseEntity.ok(messageRepository.save(message));
    }
}
