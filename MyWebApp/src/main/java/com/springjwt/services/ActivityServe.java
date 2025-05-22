package com.springjwt.services;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjwt.Exceptions.ResourceNotFound;
import com.springjwt.entities.Activity;
import com.springjwt.entities.User;
import com.springjwt.repositories.ActivityRepo;
import com.springjwt.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ActivityServe {

    @Autowired
    private ActivityRepo activityRepo;

    @Autowired
    private UserRepository userRepo;

    // Create a new activity for a user
    public Activity createActivity(Long userId, Activity activity) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found with ID: " + userId));
        activity.setUser(user);
        // 'date' will be automatically set via @PrePersist
        return activityRepo.save(activity);
    }

    // Get an activity by its ID
    @Transactional
    public Activity getActivityById(Long activityId) {
        return activityRepo.findById(activityId)
                .orElseThrow(() -> new ResourceNotFound("Activity not found with ID: " + activityId));
    }

    // Get all activities
    public List<Activity> getAllActivities() {
        return activityRepo.findAll();
    }

    // Update an existing activity (only score can be updated)
    @Transactional
    public Activity updateActivity(Long activityId, Activity updatedActivity) {
        Activity existing = getActivityById(activityId);

        if (updatedActivity.getScore() != null) {
            existing.setScore(updatedActivity.getScore());
        }

        return activityRepo.save(existing);
    }

    // Delete an activity
    public void deleteActivity(Long activityId) {
        Activity activity = getActivityById(activityId);
        activityRepo.delete(activity);
    }
    
    public Long getActivityCountByUserId(Long userId) {
        return activityRepo.countByUser_UserId(userId);  // Get count based on userId
    }
    
    public Map<String, Integer> getLast7DaysScores(Long userId) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(6);  // includes today + past 6 days

        List<Activity> activities = activityRepo.findActivitiesForLast7Days(userId, startDate, endDate);

        // Initialize map with 0 for each day
        Map<String, Integer> dailyScores = new LinkedHashMap<>();
        for (int i = 0; i < 7; i++) {
            LocalDate date = startDate.plusDays(i);
            dailyScores.put(date.toString(), 0);
        }

        // Fill in the scores per day (assuming one activity per day; if multiple, sum them)
        for (Activity activity : activities) {
            String dateStr = activity.getDate().toString();
            int scoreValue = Integer.parseInt(activity.getScore());  // Assuming score is stored as string like "5"
            dailyScores.put(dateStr, dailyScores.getOrDefault(dateStr, 0) + scoreValue);
        }

        return dailyScores;
    }
}
