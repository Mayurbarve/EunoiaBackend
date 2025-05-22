package com.springjwt.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Mood {
	
	public Mood() {
		
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mood_id")
    private Long moodId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String mood;
    private String activity;
    private String reflection;
    
    @Column(name = "join_date")
    private LocalDate date;
    
    @PrePersist
    protected void onCreate() {
        this.date = LocalDate.now();
    }

	public Mood(Long moodId, User user, String mood, String activity, String reflection, LocalDate date) {
		this.moodId = moodId;
		this.user = user;
		this.mood = mood;
		this.activity = activity;
		this.reflection = reflection;
		this.date = date;
	}

	public Long getMoodId() {
		return moodId;
	}
	public void setMoodId(Long moodId) {
		this.moodId = moodId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}
	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getReflection() {
		return reflection;
	}

	public void setReflection(String reflection) {
		this.reflection = reflection;
	}

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
    
    

	
    
    
}

