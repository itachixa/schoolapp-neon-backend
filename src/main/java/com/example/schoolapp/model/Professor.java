package com.example.schoolapp.model;

import jakarta.persistence.*;

@Entity
public class Professor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long professorId;

    @OneToOne(optional = false)
    @JoinColumn(name="userId", unique = true, nullable = false)
    private User user;

    public Long getProfessorId() { return professorId; }
    public void setProfessorId(Long professorId) { this.professorId = professorId; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
