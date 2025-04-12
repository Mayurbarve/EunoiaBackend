package HealthProject.Eunoia.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "journal_id")
    private Long journalId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "j_title", nullable = false)
    private String title;

    @Column(name = "j_content", nullable = false, length = 5000)
    private String content;

    @Column(name = "j_date", nullable = false)
    private LocalDate date;

    public Journal() {}

    public Journal(Long journalId, User user, String title, String content, LocalDate date) {
        this.journalId = journalId;
        this.user = user;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
