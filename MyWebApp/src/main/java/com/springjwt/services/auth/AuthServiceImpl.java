package com.springjwt.services.auth;

import com.springjwt.dto.SignupDTO;
import com.springjwt.dto.UserDTO;
import com.springjwt.entities.User;
import com.springjwt.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // ✅ Added encoder

    @Override
    public UserDTO createUser(SignupDTO signupDTO) {
        User user = new User();
        user.setName(signupDTO.getName());
        user.setEmail(signupDTO.getEmail());
        user.setContact(signupDTO.getContact());
        user.setAge(signupDTO.getAge());
        user.setDesignation(signupDTO.getDesignation());

        // ✅ ENCODE password before saving
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));

        User savedUser = userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(savedUser.getUserId());
        userDTO.setName(savedUser.getName());
        userDTO.setEmail(savedUser.getEmail());

        return userDTO;
    }
}
