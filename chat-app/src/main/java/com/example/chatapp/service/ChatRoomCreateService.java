package com.example.chatapp.service;

import com.example.chatapp.domain.ChatRoom;
import com.example.chatapp.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomCreateService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMemberService chatMemberService;
    private final MongoTemplate mongoTemplate;

    public ChatRoom createChatRoom(String title, List<Long> joinedMemberIds) {
        ChatRoom chatRoom = new ChatRoom(title);
        chatRoomRepository.save(chatRoom);
        joinedMemberIds.forEach(memberId -> chatMemberService.joinChatRoom(memberId, chatRoom.getId()));
        return chatRoom;
    }
}
