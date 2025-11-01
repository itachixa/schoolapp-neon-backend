package com.example.schoolapp.model;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @OneToOne(optional = false)
    @JoinColumn(name = "userId", unique = true, nullable = false)
    private User user;

    @Column(length = 100)
    private String name; // ✅ Nouvelle colonne (sera ajoutée automatiquement)

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {   // ✅ Getter
        return name;
    }

    public void setName(String name) {  // ✅ Setter
        this.name = name;
    }
}
