package com.chatproject.chat.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatproject.chat.entity.User;
import com.chatproject.chat.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void registerUser(User user) {

        if(user.getUsername().isEmpty()) {

            throw new RuntimeException(
                    "Username required"
            );
        }

        repository.save(user);
    }

    public List<User> getAllUsers() {

        return repository.findAll();
    }

    public User getById(Long id) {

        return repository.findById(id)
                .orElse(null);
    }
}