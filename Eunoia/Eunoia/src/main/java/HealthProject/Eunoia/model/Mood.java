package HealthProject.Eunoia.model;

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

    @Column(name = "m_feelings")
    private String feelings;

    @Column(name = "self_thoughts")
    private String selfThoughts;

    @Column(name = "mood_activities")
    private String moodActivities;

    @Column(name = "user_mood")
    private String mood;

	public Mood(Long moodId, User user, String feelings, String selfThoughts, String moodActivities, String mood) {
		super();
		this.moodId = moodId;
		this.user = user;
		this.feelings = feelings;
		this.selfThoughts = selfThoughts;
		this.moodActivities = moodActivities;
		this.mood = mood;
	}

	public Long getMoodId() {
		return moodId;
	}

	public void setMoodId(Long moodId) {
		this.moodId = moodId;
	}

	public User getUserId() {
		return user;
	}

	public void setUserId(User user) {
		this.user = user;
	}

	public String getFeelings() {
		return feelings;
	}

	public void setFeelings(String feelings) {
		this.feelings = feelings;
	}

	public String getSelfThoughts() {
		return selfThoughts;
	}

	public void setSelfThoughts(String selfThoughts) {
		this.selfThoughts = selfThoughts;
	}

	public String getMoodActivities() {
		return moodActivities;
	}

	public void setMoodActivities(String moodActivities) {
		this.moodActivities = moodActivities;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}
    
    
}

