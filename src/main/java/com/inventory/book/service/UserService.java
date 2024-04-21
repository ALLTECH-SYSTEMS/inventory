package com.inventory.book.service;

import com.inventory.book.entity.User;
import com.inventory.book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        return user.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }
}
