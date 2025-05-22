package com.springjwt.services ;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjwt.entities.User;
import com.springjwt.repositories.UserRepository;



@Service
public class UserServe {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long userId, User updatedUser) {
        User user = getUserById(userId);
        user.setName(updatedUser.getName());
        user.setContact(updatedUser.getContact());
        user.setEmail(updatedUser.getEmail());
        user.setAge(updatedUser.getAge());
        user.setPassword(updatedUser.getPassword());
        user.setDesignation(updatedUser.getDesignation());
        if (updatedUser.getProfileImage() != null) {
            user.setProfileImage(updatedUser.getProfileImage());
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
