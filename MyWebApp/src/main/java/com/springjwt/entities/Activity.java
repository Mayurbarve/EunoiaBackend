package com.springjwt.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "act_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Ensuring foreign key is non-nullable
    private User user;

    @Column(name = "test_date")
    private LocalDate date;

    @PrePersist
    protected void onCreate() {
        this.date = LocalDate.now();
    }

    @Column(name = "ass_result")
    private String score;

    public Activity() {}


    public Activity(Long id, User user, LocalDate date, String score) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.score = score;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getScore() { return score; }
    public void setScore(String score) { this.score = score; }
}
