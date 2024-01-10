package com.example.chatapp.service;

import com.example.chatapp.domain.ChatMember;
import com.example.chatapp.domain.subDoc.JoinedChatRoom;
import com.example.chatapp.repository.ChatMemberRepository;
import com.mongodb.BasicDBObject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ChatMemberService {
    private final ChatMemberRepository chatMemberRepository;
    private final MongoTemplate mongoTemplate;

    public ChatMember getChatMember(Long memberId) {
        return chatMemberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );
    }

    public void joinChatRoom(Long memberId, String chatRoomId) {
        Query query = new Query(Criteria.where("_id").is(memberId)
                .and("joinedChatRooms.chatRoomId").ne(chatRoomId));
        Update update = new Update().addToSet("joinedChatRooms", new JoinedChatRoom(chatRoomId, new Date()));
        mongoTemplate.updateFirst(query, update, ChatMember.class);
    }

    public void leaveChatRoom(Long memberId, String chatRoomId, Date leaveTime) {
        Query query = new Query(Criteria.where("_id").is(memberId)
                .and("joinedChatRooms.chatRoomId").is(chatRoomId));
        Update update = new Update().set("joinedChatRooms.$.lastAccessTime", leaveTime);
        mongoTemplate.updateFirst(query, update, ChatMember.class);
    }

    public void deleteChatRoom(Long memberId, String chatRoomId) {
        Query query = new Query(Criteria.where("_id").is(memberId));
        Update update = new Update().pull("joinedChatRooms", new BasicDBObject("chatRoomId", chatRoomId));
        mongoTemplate.updateFirst(query, update, ChatMember.class);
    }

}
