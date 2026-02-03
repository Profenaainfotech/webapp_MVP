package com.example.Profenaa_touch.service;

import com.example.Profenaa_touch.entity.Role;
import com.example.Profenaa_touch.entity.User;
import com.example.Profenaa_touch.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean exists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }


    public void register(String name, String email) {

        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setVerified(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setRole(Role.USER);


        userRepository.save(user);
    }
}
