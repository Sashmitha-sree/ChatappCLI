package com.chatproject.chat.service;

import com.chatproject.chat.entity.*;
import com.chatproject.chat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserService userService;

    public void createChat(Chat chat) {

        chatRepository.save(chat);
    }

    public void addMember(
            Long memberId,
            Long chatId,
            Long userId
    ) {

        Chat chat = chatRepository.findById(chatId)
                .orElse(null);

        User user =
                userService.getById(userId);

        if(chat == null || user == null) {

            throw new RuntimeException(
                    "Chat/User not found"
            );
        }

        Member member =
                new Member(
                        memberId,
                        chat,
                        user
                );

        memberRepository.save(member);
    }
}