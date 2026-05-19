package com.chatproject.chat.repository;
import java.util.List;

import com.chatproject.chat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChat_ChatIdOrderBySequenceNumber(
            Long chatId
    );
}