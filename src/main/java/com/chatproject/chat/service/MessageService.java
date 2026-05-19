package com.chatproject.chat.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatproject.chat.entity.Chat;
import com.chatproject.chat.entity.Member;
import com.chatproject.chat.entity.Message;
import com.chatproject.chat.entity.MessageStatus;
import com.chatproject.chat.entity.MessageStatusType;
import com.chatproject.chat.entity.User;
import com.chatproject.chat.repository.ChatRepository;
import com.chatproject.chat.repository.MemberRepository;
import com.chatproject.chat.repository.MessageRepository;
import com.chatproject.chat.repository.MessageStatusRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageStatusRepository statusRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MemberRepository memberRepository;

    public void sendMessage( Long messageId,Long chatId,Long senderId,String text,Long sequence) 
    {

        Chat chat =chatRepository.findById(chatId).orElse(null);

        User sender =userService.getById(senderId);

        if(chat == null || sender == null) {

            throw new RuntimeException("Chat/User not found");
        }

        Message message =new Message(messageId, chat, sender, text, LocalDateTime.now(), sequence  );

        messageRepository.save(message);

        List<Member> members = memberRepository.findByChat_ChatId(chatId);

        long statusId = 1;

        for(Member member : members) {

            if(member.getUser().getUserId() .equals(senderId)) {

                continue;
            }

            MessageStatusType status;

            if(member.getUser().isOnline()) {

                status = MessageStatusType.DELIVERED;
            }

            else {

                status =MessageStatusType.SENT;
            }

            MessageStatus messageStatus = new MessageStatus( statusId++ ,message , member.getUser(), status );

            statusRepository.save(messageStatus);
        }
    }

}