package com.example.chatapp.service;

import com.example.chatapp.domain.ChatMember;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChatMemberServiceTest {
    @Autowired
    private ChatMemberService chatMemberService;

    @Autowired
    private ChatMemberCreateService chatMemberCreateService;

    @Test
    void leaveChatRoom() {
        ChatMember chatMember = chatMemberCreateService.createChatMember(1L);
        chatMemberService.joinChatRoom(1L, "1");
        Date lastAccessTimeBefore = chatMemberService.getChatMember(1L).getJoinedChatRooms().get(0).getLastAccessTime();
        chatMemberService.leaveChatRoom(1L, "1", new Date());
        Date lastAccessTimeAfter = chatMemberService.getChatMember(1L).getJoinedChatRooms().get(0).getLastAccessTime();

        System.out.println(lastAccessTimeBefore.getTime());
        System.out.println(lastAccessTimeAfter.getTime());
        Assertions.assertThat(lastAccessTimeBefore).isBefore(lastAccessTimeAfter);





    }
}