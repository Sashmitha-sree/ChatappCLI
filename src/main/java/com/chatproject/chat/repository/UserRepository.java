package com.chatproject.chat.repository;


import com.chatproject.chat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Long> {
}