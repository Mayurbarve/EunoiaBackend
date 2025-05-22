package com.springjwt.services ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjwt.Exceptions.ResourceNotFound;
import com.springjwt.dto.MoodEntryDto;
import com.springjwt.entities.Mood;
import com.springjwt.entities.User;
import com.springjwt.repositories.MoodRepo;
import com.springjwt.repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoodServe {

    @Autowired
    private MoodRepo moodRepo;

    @Autowired
    private UserRepository userRepo;

    // Get all moods
    public List<Mood> getAllMoods() {
        return moodRepo.findAll();
    }

    // Get mood by ID
    public Mood getMoodById(Long moodId) {
        return moodRepo.findById(moodId)
                .orElseThrow(() -> new ResourceNotFound("Mood entry not found"));
    }

    // Add mood
    public Mood addMood(Long userId, Mood mood) {
        User user = userRepo.findById(userId)
            .orElseThrow(() -> new ResourceNotFound("User not found with id: " + userId));

        mood.setUser(user);
        return moodRepo.save(mood);
    }

    // Update mood
    public Mood updateMood(Long moodId, Mood moodDetails) {
        Mood mood = getMoodById(moodId);

        if (moodDetails.getMood() != null) {
            mood.setMood(moodDetails.getMood());
        }

        if (moodDetails.getActivity() != null) {
            mood.setActivity(moodDetails.getActivity());
        }

        if (moodDetails.getReflection() != null) {
            mood.setReflection(moodDetails.getReflection());
        }

        return moodRepo.save(mood);
    }

    // Delete mood
    public void deleteMood(Long moodId) {
        Mood mood = getMoodById(moodId);
        moodRepo.delete(mood);
    }
    
    public Long getMoodCountByUserId(Long userId) {
        return moodRepo.countByUser_UserId(userId);  // Get count based on userId
    }
    
    public List<MoodEntryDto> getMoodEntriesForWeek(Long userId) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(java.time.DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(java.time.DayOfWeek.SUNDAY);

        List<Mood> moods = moodRepo.findMoodsForUserThisWeek(userId, startOfWeek, endOfWeek);

        return moods.stream()
                .map(mood -> new MoodEntryDto(mood.getDate().toString(), mood.getMood()))
                .collect(Collectors.toList());
    }
    
}
