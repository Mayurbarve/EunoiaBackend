package com.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springjwt.entities.User;
import com.springjwt.services.UserServe;



@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServe userServe;

    // Create a new user 
    @PostMapping("/create")
    public ResponseEntity<User> createUser(
            @RequestParam("name") String name,
            @RequestParam("contact") Long contact,
            @RequestParam("email") String email,
            @RequestParam("age") Integer age,
            @RequestParam("password") String password,
            @RequestParam("designation") String designation,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) {

        try {
            User user = new User();
            user.setName(name);
            user.setContact(contact);
            user.setEmail(email);
            user.setAge(age);
            user.setPassword(password);
            user.setDesignation(designation);

            if (profileImage != null && !profileImage.isEmpty()) {
                user.setProfileImage(profileImage.getBytes());
            }

            User savedUser = userServe.createUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/image/{userId}")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable Long userId) {
        User user = userServe.getUserById(userId);
        byte[] imageData = user.getProfileImage();

        return ResponseEntity.ok()
            .header("Content-Type", "image/jpeg") // or "image/png"
            .body(imageData);
    }

    // Get user by ID  
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
    	User us = userServe.getUserById(userId);
        return ResponseEntity.ok(us);
    }
    

    // Get all users  
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
    	List <User> list = userServe.getAllUsers();
		return ResponseEntity.ok(list);
    }
    

    // Update user details   
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        return ResponseEntity.ok(userServe.updateUser(userId, updatedUser));
    }

    // Delete user by ID   
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userServe.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}