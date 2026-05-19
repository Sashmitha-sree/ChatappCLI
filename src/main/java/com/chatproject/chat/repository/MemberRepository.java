package com.chatproject.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatproject.chat.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByChat_ChatId(Long chatId);
}