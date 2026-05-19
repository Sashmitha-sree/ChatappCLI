package com.chatproject.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatproject.chat.entity.Chat;

public interface ChatRepository
        extends JpaRepository<Chat, Long> {

}