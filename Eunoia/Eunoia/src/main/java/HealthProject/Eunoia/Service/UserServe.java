package HealthProject.Eunoia.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import HealthProject.Eunoia.Exceptions.ResourceNotFound;
import HealthProject.Eunoia.Repository.UserRepository;
import HealthProject.Eunoia.model.User;

@Service
public class UserServe {
    
    @Autowired // Dependency Injection
    private UserRepository userRepo;

    // Create a new user
    public User createUser(User user) {
        return userRepo.save(user);
    }

    // Get user by ID
    public User getUserById(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found with ID: " + userId));
    }
   
    

    // Get all users
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    
    // Update user details
    public User updateUser(Long userId, User updatedUser) {
        User existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found with ID: " + userId));

        if (updatedUser.getName() != null) {
            existingUser.setName(updatedUser.getName());
        }
        if (updatedUser.getContact() != null) {
            existingUser.setContact(updatedUser.getContact());
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getAge() != null) {
            existingUser.setAge(updatedUser.getAge());
        }
        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(updatedUser.getPassword());
        }
        if (updatedUser.getDesignation() != null) {
            existingUser.setDesignation(updatedUser.getDesignation());
        }
        if (updatedUser.getDate() != null) {
            existingUser.setDate(updatedUser.getDate());
        }

        return userRepo.save(existingUser);
    }

    
    // Delete user by ID
    public void deleteUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found with ID: " + userId));
        userRepo.delete(user);
    }
}
