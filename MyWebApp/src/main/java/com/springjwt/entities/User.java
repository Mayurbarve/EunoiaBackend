package com.springjwt.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "user_id")
	    private Long userId;

	    @Column(name = "user_name")
	    private String name;

	    @Column(name = "user_contact")
	    private Long contact;

	    @Column(name = "user_email")
	    private String email;

	    @Column(name = "user_age")
	    private Integer age;

	    @Column(name = "user_pass")
	    private String password;

	    @Column(name = "user_desig")
	    private String designation;
	   
	    @Lob
	    @Column(name = "user_image", columnDefinition = "MEDIUMBLOB")
	    private byte[] profileImage; 
	        
		@Column(name = "join_date")
	    private LocalDate date;

	    @PrePersist
	    protected void onCreate() {
	        this.date = LocalDate.now();
	    }
	    
	    // One User -> Many Activities (One-to-Many Relationship)
	    @JsonIgnore
	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	    private List<Activity> activities = new ArrayList<>();
	    

	    @JsonIgnore
	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Appointment> appointments = new ArrayList<>();

	    @JsonIgnore
	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Journal> journals = new ArrayList<>();

	    @JsonIgnore
	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Mood> moods = new ArrayList<>();

	    // Default constructor
	    public User() {}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Long getContact() {
			return contact;
		}

		public void setContact(Long contact) {
			this.contact = contact;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public byte[] getProfileImage() {
			return profileImage;
		}

		public void setProfileImage(byte[] profileImage) {
			this.profileImage = profileImage;
		}

		public LocalDate getDate() {
			return date;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}

		public List<Activity> getActivities() {
			return activities;
		}

		public void setActivities(List<Activity> activities) {
			this.activities = activities;
		}

		public List<Appointment> getAppointments() {
			return appointments;
		}

		public void setAppointments(List<Appointment> appointments) {
			this.appointments = appointments;
		}

		public List<Journal> getJournals() {
			return journals;
		}

		public void setJournals(List<Journal> journals) {
			this.journals = journals;
		}

		public List<Mood> getMoods() {
			return moods;
		}

		public void setMoods(List<Mood> moods) {
			this.moods = moods;
		}
	    
	    
	
}
