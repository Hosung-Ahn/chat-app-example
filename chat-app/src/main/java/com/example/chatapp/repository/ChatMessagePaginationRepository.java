package com.example.chatapp.repository;

import com.example.chatapp.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatMessagePaginationRepository {
    private final MongoTemplate mongoTemplate;

    public List<ChatMessage> getMessagesBeforeDateByChatRoomID(String chatRoomId, Date cursor, int limit) {
        Query query = new Query()
                .addCriteria(Criteria.where("chatRoomId").is(chatRoomId))
                .addCriteria(Criteria.where("sendTime").lt(cursor))
                .limit(limit)
                .with(Sort.by(Sort.Direction.DESC, "sendTime"));

        return mongoTemplate.find(query, ChatMessage.class);
    }
}
