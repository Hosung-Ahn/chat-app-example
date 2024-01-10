package com.example.chatapp.service;

import com.example.chatapp.domain.ChatMessage;
import com.example.chatapp.domain.ChatRoom;
import com.example.chatapp.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final MongoTemplate mongoTemplate;
    private final ChatMessageRepository chatMessageRepository;
    public void sendMessage(String chatRoomId, Long senderId, String content) {
        ChatMessage chatMessage = new ChatMessage(chatRoomId, senderId, content);
        chatMessageRepository.save(chatMessage);
        addMessageToChatRoom(chatRoomId, chatMessage);
    }

    private void addMessageToChatRoom(String chatRoomId, ChatMessage message) {
        Query query = new Query(Criteria.where("_id").is(chatRoomId));
        Update update = new Update().push("lastMessages")
                        .slice(-100)
                        .each(message);
        mongoTemplate.updateFirst(query, update, ChatRoom.class);
    }
}
