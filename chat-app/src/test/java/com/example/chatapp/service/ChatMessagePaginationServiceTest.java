package com.example.chatapp.service;

import com.example.chatapp.domain.ChatMessage;
import com.example.chatapp.domain.ChatRoom;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChatMessagePaginationServiceTest {
    @Autowired
    private ChatMessagePaginationService chatMessagePaginationService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private ChatRoomCreateService chatRoomCreateService;

    @Test
    void paginationTest() {
        ChatRoom chatRoom = chatRoomCreateService.createChatRoom("test", List.of(1L, 2L));
        for (int i=0; i<20; i++) {
            chatMessageService.sendMessage(chatRoom.getId(), 1L, "test" + i);
        }
        Date date = new Date();
        for (int i=20;i<40;i++) {
            chatMessageService.sendMessage(chatRoom.getId(), 1L, "test" + i);
        }

        List<ChatMessage> messages = chatMessagePaginationService.getMessagesBeforeDateByChatRoomID(chatRoom.getId(), date, 10);
        assertThat(messages.size()).isEqualTo(10);

        messages = chatMessagePaginationService.getMessagesBeforeDateByChatRoomID(chatRoom.getId(), date, 40);
        assertThat(messages.size()).isEqualTo(20);
    }
}