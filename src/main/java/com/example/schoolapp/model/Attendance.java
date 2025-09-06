package com.example.schoolapp.model;

import jakarta.persistence.*;

@Entity
public class Attendance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    @ManyToOne(optional = false) @JoinColumn(name="studentId", nullable=false)
    private Student student;

    @ManyToOne(optional = false) @JoinColumn(name="professorId", nullable=false)
    private Professor professor;

    @Column(nullable = false)
    private java.time.LocalDate date;

    @Column(nullable = false, length = 10)
    private String status; // present / absent

    public Long getAttendanceId() { return attendanceId; }
    public void setAttendanceId(Long attendanceId) { this.attendanceId = attendanceId; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }
    public java.time.LocalDate getDate() { return date; }
    public void setDate(java.time.LocalDate date) { this.date = date; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
