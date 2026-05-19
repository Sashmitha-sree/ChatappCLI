package com.chatproject.chat.controller;



import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.chatproject.chat.entity.Chat;
import com.chatproject.chat.entity.ChatType;
import com.chatproject.chat.entity.User;
import com.chatproject.chat.service.ChatService;
import com.chatproject.chat.service.MessageService;
import com.chatproject.chat.service.UserService;

@Component
public class CliController
        implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageService messageService;

    @Override
    public void run(String... args) {

        Scanner sc = new Scanner(System.in);

        while(true) {

            System.out.println("\n===== MENU =====");

            System.out.println("1 Register User");
            System.out.println("2 View Users");

            System.out.println("3 Create Chat");

            System.out.println("4 Add Member");

            System.out.println("5 Send Message");

            System.out.println("6 Exit");

            int choice = sc.nextInt();

            switch(choice) {

                case 1 -> {
                    System.out.print("User ID: ");
                    Long userId = sc.nextLong();

                    sc.nextLine();

                    System.out.print("Username: ");

                    String username =sc.nextLine();

                    System.out.print("Online (true/false): ");

                    boolean online =sc.nextBoolean();

                    User user =new User(userId,username,online);

                    userService.registerUser(user);

                    System.out.println("User Registered");
                }

                case 2 -> userService.getAllUsers().forEach(System.out::println);

                case 3 -> {
                    System.out.print("Chat ID: ");

                    Long chatId = sc.nextLong();

                    System.out.println("1 PRIVATE");

                    System.out.println("2 GROUP");

                    int type = sc.nextInt();

                    ChatType chatType;

                    if(type == 1) {

                        chatType =ChatType.PRIVATE;
                    }

                    else {

                        chatType =ChatType.GROUP;
                    }

                    Chat chat =new Chat( chatId,chatType);

                    chatService.createChat(chat);

                    System.out.println("Chat Created");
                }

                case 4 -> {
                    System.out.print("Member ID: ");

                    Long memberId = sc.nextLong();

                    System.out.print("Chat ID: ");

                    Long cId =sc.nextLong();

                    System.out.print("User ID: ");

                    Long uId =sc.nextLong();

                    chatService.addMember(memberId,cId,uId);

                    System.out.println("Member Added");
                }

                case 5 -> {
                    System.out.print("Message ID: ");

                    Long messageId =sc.nextLong();

                    System.out.print("Chat ID: ");

                    Long chat =sc.nextLong();

                    System.out.print("Sender ID: ");

                    Long sender =sc.nextLong();

                    sc.nextLine();

                    System.out.print("Message: ");

                    String text =sc.nextLine();

                    System.out.print("Sequence Number: " );

                    Long seq =sc.nextLong();

                    messageService.sendMessage( messageId,chat, sender,text,seq );

                    System.out.println("Message Sent" );
                }

               

                case 6 -> System.exit(0);
            }
        }
    }
}