package HealthProject.Eunoia.Service;

import HealthProject.Eunoia.Exceptions.ResourceNotFound;
import HealthProject.Eunoia.Repository.ActivityRepo;
import HealthProject.Eunoia.Repository.UserRepository;
import HealthProject.Eunoia.model.Activity;
import HealthProject.Eunoia.model.User;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // Update an existing activity
    @Transactional
    public Activity updateActivity(Long activityId, Activity updatedActivity) {
        Activity existing = getActivityById(activityId);
        
        if(updatedActivity.getTask() != null) {
        	existing.setTask(updatedActivity.getTask());
        }

        if(updatedActivity.getDueDate() != null) {
        	existing.setDueDate(updatedActivity.getDueDate());
        }
        
        if(updatedActivity.getAssessmentResult() != null) {
        	existing.setAssessmentResult(updatedActivity.getAssessmentResult());
        }
        if(updatedActivity.getGameScore() != null) {
        	existing.setGameScore(updatedActivity.getGameScore());
        }
        return activityRepo.save(existing);
    }
    
  

    // Delete an activity
    public void deleteActivity(Long activityId) {
        Activity activity = getActivityById(activityId);
        activityRepo.delete(activity);
    }
}
