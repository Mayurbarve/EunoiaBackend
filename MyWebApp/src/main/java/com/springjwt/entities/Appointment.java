package com.springjwt.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apt_id")
    private Long aptId;
    
    @Column(name = "apt_date")
    private LocalDate aptDate;

    @Column(name = "doc_details")
    private String docDetails;


    // ✅ Many Appointments -> One User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Many Appointments -> One Doctor
    @ManyToOne
    @JoinColumn(name = "doc_id", nullable = false)
    private Doctor doctor;
    
    private boolean remark;
    


    public boolean isRemark() {
		return remark;
	}

	public void setRemark(boolean remark) {
		this.remark = remark;
	}

	public Appointment() {}

    // Getters and Setters
    public Long getAptId() { return aptId; }
    public void setAptId(Long aptId) { this.aptId = aptId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDate getAptDate() { return aptDate; }
    public void setAptDate(LocalDate aptDate) { this.aptDate = aptDate; }

    public String getDocDetails() { return docDetails; }
    public void setDocDetails(String docDetails) { this.docDetails = docDetails; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
}
