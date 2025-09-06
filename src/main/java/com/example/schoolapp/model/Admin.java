package com.example.schoolapp.model;

import jakarta.persistence.*;

@Entity
public class Admin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @OneToOne(optional = false)
    @JoinColumn(name="userId", unique = true, nullable = false)
    private User user;

    public Long getAdminId() { return adminId; }
    public void setAdminId(Long adminId) { this.adminId = adminId; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
