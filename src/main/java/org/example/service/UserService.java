package org.example.service;


import org.example.model.user.Customer;
import org.example.model.user.Seller;
import org.example.model.user.User;
import org.example.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.authenticate(username, password)) {
            return user;
        }
        return null;
    }

    public void updateProfile(User user) {
        user.updateProfile();
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}