package HealthProject.Eunoia.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import HealthProject.Eunoia.Exceptions.ResourceNotFound;
import HealthProject.Eunoia.Repository.MoodRepo;
import HealthProject.Eunoia.Repository.UserRepository;
import HealthProject.Eunoia.model.Mood;
import HealthProject.Eunoia.model.User;

import java.util.List;

@Service
public class MoodServe {

    @Autowired
    private MoodRepo moodRepo;
    @Autowired
    private UserRepository userRepo;

    
    //get all mood
    public List<Mood> getAllMoods() {
        return moodRepo.findAll();
    }
    
    //get mood by id
    public Mood getMoodById(Long moodId) {
        return moodRepo.findById(moodId)
                .orElseThrow(() -> new ResourceNotFound("Mood entry not found"));
    }
    
    
    
    public Mood addMood(Long userId, Mood mood) {
        User user = userRepo.findById(userId)
            .orElseThrow(() -> new ResourceNotFound("User not found with id: " + userId));
        mood.setUserId(user);
        return moodRepo.save(mood);
    }

   
    //update mood
//    public Mood updateMood(long moodId, Mood moodDetails) {
//        Mood mood = getMoodById(moodId);
//        mood.setFeelings(moodDetails.getFeelings());
//        mood.setSelfThoughts(moodDetails.getSelfThoughts());
//        mood.setMoodActivities(moodDetails.getMoodActivities());
//        mood.setMood(moodDetails.getMood());
//        return moodRepo.save(mood);
//    }

    public Mood updateMood(Long moodId, Mood moodDetails) {
        Mood mood = getMoodById(moodId);

        if (moodDetails.getFeelings() != null) {
            mood.setFeelings(moodDetails.getFeelings());
        }
        if (moodDetails.getSelfThoughts() != null) {
            mood.setSelfThoughts(moodDetails.getSelfThoughts());
        }
        if (moodDetails.getMoodActivities() != null) {
            mood.setMoodActivities(moodDetails.getMoodActivities());
        }
        if (moodDetails.getMood() != null) {
            mood.setMood(moodDetails.getMood());
        }

        return moodRepo.save(mood);
    }

    //delete mood
    public void deleteMood(Long moodId) {
        Mood mood = getMoodById(moodId);
        moodRepo.delete(mood);
    }
}
