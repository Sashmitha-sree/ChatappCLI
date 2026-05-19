package com.chatproject.chat.repository;



import com.chatproject.chat.entity.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageStatusRepository extends JpaRepository<MessageStatus, Long> {
}