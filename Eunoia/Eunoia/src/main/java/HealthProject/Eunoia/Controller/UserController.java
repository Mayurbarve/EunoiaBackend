package HealthProject.Eunoia.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import HealthProject.Eunoia.Service.UserServe;
import HealthProject.Eunoia.model.User;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServe userServe;

    // Create a new user 
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userServe.createUser(user));
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
    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        return ResponseEntity.ok(userServe.updateUser(userId, updatedUser));
    }

    // Delete user by ID   
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userServe.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}