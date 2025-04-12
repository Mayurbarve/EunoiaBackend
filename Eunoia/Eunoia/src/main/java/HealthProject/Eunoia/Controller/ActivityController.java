package HealthProject.Eunoia.Controller;

import HealthProject.Eunoia.Exceptions.ResourceNotFound;
import HealthProject.Eunoia.Service.ActivityServe;
import HealthProject.Eunoia.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/users/activities")
public class ActivityController {

    @Autowired
    private ActivityServe activityServe;

    // Create a new activity for a user
    @PostMapping("/create/{userId}")
    public ResponseEntity<Activity> createActivity(
            @PathVariable Long userId,
            @RequestBody Activity activity) {
        Activity created = activityServe.createActivity(userId, activity);
        return ResponseEntity.ok(created);
    }

    // Get all activities
    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities() {
        List<Activity> activities = activityServe.getAllActivities();
        return ResponseEntity.ok(activities);
    }

    // Get a single activity by ID
    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        Activity activity = activityServe.getActivityById(id);
        return ResponseEntity.ok(activity);
    }

    // Update an activity
    @PutMapping("/update/{id}")
    public ResponseEntity<Activity> updateActivity(
            @PathVariable Long id,
            @RequestBody Activity activity) {
        Activity updated = activityServe.updateActivity(id, activity);
        return ResponseEntity.ok(updated);
    }

    // Delete an activity
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityServe.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }

    // Handle ResourceNotFound exceptions
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
