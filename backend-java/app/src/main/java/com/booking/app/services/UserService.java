package com.booking.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.booking.app.entities.User;
import com.booking.app.models.AuthCredentials;
import com.booking.app.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;


    public User savUser(AuthCredentials user) {
        
        String email = user.getEmail();
        String password = user.getPassword();

        if(userRepository.findByEmail(email) != null) {
            throw new RuntimeException("User already exists");
        }

        String encodedPassword = passwordEncoder.encode(password);
        
        User newUser = new User();

        newUser.setEmail(email);
        newUser.setPassword(encodedPassword);
        return userRepository.save(newUser);
    }

    public String loginUser(AuthCredentials user) {
        
        String email = user.getEmail();
        String password = user.getPassword();

        User existingUser = userRepository.findByEmail(email);

        if(existingUser == null) {
            throw new RuntimeException("User not found");
        }

        if(!passwordEncoder.matches(password, existingUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtService.generateToken(existingUser);
    }
}
