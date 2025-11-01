package com.example.schoolapp.repository;

import com.example.schoolapp.model.Message;
import com.example.schoolapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // 🔹 Tous les messages entre deux utilisateurs (dans les deux sens)
    @Query("SELECT m FROM Message m WHERE " +
           "(m.sender = :user1 AND m.receiver = :user2) OR " +
           "(m.sender = :user2 AND m.receiver = :user1) " +
           "ORDER BY m.timestamp ASC")
    List<Message> findConversation(User user1, User user2);

    // 🔹 Tous les messages envoyés ou reçus par un utilisateur
    List<Message> findBySenderOrReceiver(User sender, User receiver);
}
