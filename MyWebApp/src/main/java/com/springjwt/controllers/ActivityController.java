package com.springjwt.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springjwt.Exceptions.ResourceNotFound;
import com.springjwt.entities.Activity;
import com.springjwt.services.ActivityServe;


// Adjust if needed
@RestController
@RequestMapping("/users/activities")
public class ActivityController {

    @Autowired
    private ActivityServe activityServe;

    // Create a new activity for a user
    @PostMapping("/{userId}")
    public ResponseEntity<Activity> createActivity(
            @PathVariable Long userId,
            @RequestBody Activity activity) {
        activity.setId(null); // ensure it's treated as a new activity
        Activity created = activityServe.createActivity(userId, activity);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Get all activities
    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities() {
        return ResponseEntity.ok(activityServe.getAllActivities());
    }

    // Get a single activity by ID
    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        return ResponseEntity.ok(activityServe.getActivityById(id));
    }

    // Update an activity
    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(
            @PathVariable Long id,
            @RequestBody Activity activity) {
        return ResponseEntity.ok(activityServe.updateActivity(id, activity));
    }

    // Delete an activity
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityServe.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }

    // Handle ResourceNotFound exceptions
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    
    @GetMapping("/{userId}/count")
    public ResponseEntity<Long> getActivityCount(@PathVariable Long userId) {
        Long count = activityServe.getActivityCountByUserId(userId);
        return ResponseEntity.ok(count);
    }
   
    
    @GetMapping("/weekly-scores/{userId}")
    public Map<String, Integer> getWeeklyScores(@PathVariable Long userId) {
        return activityServe.getLast7DaysScores(userId);
    }
    
}
 
