package com.example.chatapp.repository;

import com.example.chatapp.domain.ChatMember;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMemberRepository extends MongoRepository<ChatMember, Long> {
}
