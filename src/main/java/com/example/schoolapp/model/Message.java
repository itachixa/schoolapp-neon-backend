package com.example.schoolapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    // 🔹 Relation avec User (expéditeur)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    // 🔹 Relation avec User (destinataire)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    // 🔹 Contenu du message
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    // 🔹 Horodatage automatique
    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    // --- Getters & Setters ---
    public Long getMessageId() { return messageId; }
    public void setMessageId(Long messageId) { this.messageId = messageId; }

    public User getSender() { return sender; }
    public void setSender(User sender) { this.sender = sender; }

    public User getReceiver() { return receiver; }
    public void setReceiver(User receiver) { this.receiver = receiver; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
