package com.example.schoolapp.model;

import jakarta.persistence.*;

@Entity
public class Grade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;

    @ManyToOne(optional = false) @JoinColumn(name="studentId", nullable=false)
    private Student student;

    @ManyToOne(optional = false) @JoinColumn(name="professorId", nullable=false)
    private Professor professor;

    @Column(nullable = false, length = 100)
    private String course;

    @Column(nullable = false)
    private Double score;

    public Long getGradeId() { return gradeId; }
    public void setGradeId(Long gradeId) { this.gradeId = gradeId; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
}
