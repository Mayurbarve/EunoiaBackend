package com.springjwt.dto;

public class MoodEntryDto {

    private String date;
    private String mood;

    public MoodEntryDto() {}

    public MoodEntryDto(String date, String mood) {
        this.date = date;
        this.mood = mood;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
