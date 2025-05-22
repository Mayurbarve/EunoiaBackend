package com.springjwt.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springjwt.dto.MoodEntryDto;
import com.springjwt.entities.Mood;
import com.springjwt.services.MoodServe;

import java.util.List;

@RestController 
@RequestMapping("/users/moods")
public class MoodController {

    @Autowired
    private MoodServe moodServe;

    // Add a new mood entry  
    @PostMapping("/{userId}")
    public ResponseEntity<Mood> addMood(@RequestBody Mood mood, @PathVariable("userId") Long userId) {
        Mood createdMood = moodServe.addMood(userId, mood);
        return ResponseEntity.ok(createdMood);
    }

    // Get all mood entries 
    @GetMapping
    public ResponseEntity<List<Mood>> getAllMoods() {
        List<Mood> moods = moodServe.getAllMoods();
        return ResponseEntity.ok(moods);
    }

    // Get mood entry by ID 
    @GetMapping("/{moodId}")
    public ResponseEntity<Mood> getMoodById(@PathVariable("moodId") Long moodId) {
        Mood mood = moodServe.getMoodById(moodId);
        return ResponseEntity.ok(mood);
    }

    // Update an existing mood entry   
    @PutMapping("/{moodId}")
    public ResponseEntity<Mood> updateMood(@RequestBody Mood moodDetails, @PathVariable("moodId") Long moodId) {
        Mood updatedMood = moodServe.updateMood(moodId, moodDetails);
        return ResponseEntity.ok(updatedMood);
    }

    // Delete a mood entry  
    @DeleteMapping("/{moodId}")
    public ResponseEntity<Void> deleteMood(@PathVariable("moodId") Long moodId) {
        moodServe.deleteMood(moodId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{userId}/count")
    public ResponseEntity<Long> getMoodCount(@PathVariable Long userId) {
        Long count = moodServe.getMoodCountByUserId(userId);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/week/{userId}")
    public ResponseEntity<List<MoodEntryDto>> getMoodEntriesForWeek(@PathVariable Long userId) {
        List<MoodEntryDto> moods = moodServe.getMoodEntriesForWeek(userId);
        if (moods.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(moods);
    }
    
 
}
