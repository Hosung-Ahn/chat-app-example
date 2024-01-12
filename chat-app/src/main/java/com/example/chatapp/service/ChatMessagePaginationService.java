package com.example.chatapp.service;

import com.example.chatapp.domain.ChatMessage;
import com.example.chatapp.repository.ChatMessagePaginationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessagePaginationService {
    private final ChatMessagePaginationRepository chatMessagePaginationRepository;

    public List<ChatMessage> getMessagesBeforeDateByChatRoomID(String chatRoomId, Date cursor, int limit) {
        return chatMessagePaginationRepository.getMessagesBeforeDateByChatRoomID(chatRoomId, cursor, limit);
    }
}
