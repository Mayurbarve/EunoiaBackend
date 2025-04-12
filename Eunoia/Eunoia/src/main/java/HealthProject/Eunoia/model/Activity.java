package HealthProject.Eunoia.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "act_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "task", nullable = false)
    private String task;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "ass_result")
    private String assessmentResult;

    @Column(name = "game_score")
    private Integer gameScore;

    public Activity() {}

	public Activity(Long id, User user, String task, LocalDate dueDate, String assessmentResult, Integer gameScore) {
		super();
		this.id = id;
		this.user = user;
		this.task = task;
		this.dueDate = dueDate;
		this.assessmentResult = assessmentResult;
		this.gameScore = gameScore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getAssessmentResult() {
		return assessmentResult;
	}

	public void setAssessmentResult(String assessmentResult) {
		this.assessmentResult = assessmentResult;
	}

	public Integer getGameScore() {
		return gameScore;
	}

	public void setGameScore(Integer gameScore) {
		this.gameScore = gameScore;
	}

   
}
