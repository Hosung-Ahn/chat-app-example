package com.example.chatapp.service;

import com.example.chatapp.domain.ChatRoom;
import com.example.chatapp.service.dto.ChatRoomByMemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class ChatRoomServiceTest {
    @Autowired
    private ChatRoomSearchService chatRoomSearchService;

    @Autowired
    private ChatRoomCreateService chatRoomCreateService;

    @Autowired
    private ChatMemberService chatMemberService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private ChatMemberCreateService chatMemberCreateService;

    @Test
    void searchChatRoom() {
        chatMemberCreateService.createChatMember(1L);
        chatMemberCreateService.createChatMember(2L);

        ChatRoom chatRoom = chatRoomCreateService.createChatRoom("테스트 채팅방", List.of(1L, 2L));
        chatMemberService.joinChatRoom(1L, chatRoom.getId());
        chatMemberService.joinChatRoom(2L, chatRoom.getId());

        for (int i=0;i<5;i++) {
            chatMessageService.sendMessage(chatRoom.getId(), 2L, "안녕하세요");
        }
        chatMemberService.leaveChatRoom(2L, chatRoom.getId(), new Date());

        for (int i=0;i<5;i++) {
            chatMessageService.sendMessage(chatRoom.getId(), 1L, "안녕하세요");
        }
        chatMemberService.leaveChatRoom(1L, chatRoom.getId(), new Date());

        List<ChatRoomByMemberDto> chatRoomsBy1 = chatRoomSearchService.getChatRoomsByMemberId(1L);
        List<ChatRoomByMemberDto> chatRoomsBy2 = chatRoomSearchService.getChatRoomsByMemberId(2L);

        Assertions.assertThat(chatRoomsBy1.get(0).getUnreadCount()).isEqualTo(0);
        Assertions.assertThat(chatRoomsBy2.get(0).getUnreadCount()).isEqualTo(5);
    }

    @Test
    void searchChatRoomsOver100() {
        chatMemberCreateService.createChatMember(1L);
        chatMemberCreateService.createChatMember(2L);

        ChatRoom chatRoom = chatRoomCreateService.createChatRoom("테스트 채팅방", List.of(1L, 2L));
        chatMemberService.joinChatRoom(1L, chatRoom.getId());
        chatMemberService.joinChatRoom(2L, chatRoom.getId());

        for (int i=0;i<105;i++) {
            chatMessageService.sendMessage(chatRoom.getId(), 2L, "안녕하세요");
        }

        List<ChatRoomByMemberDto> chatRoomsBy1 = chatRoomSearchService.getChatRoomsByMemberId(1L);

        Assertions.assertThat(chatRoomsBy1.get(0).getUnreadCount()).isEqualTo(100);
    }
}