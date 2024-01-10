package com.example.chatapp.service;

import com.example.chatapp.domain.ChatMember;
import com.example.chatapp.repository.ChatMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMemberCreateService {
    private final ChatMemberRepository chatMemberRepository;

    public ChatMember createChatMember(Long memberId) {
        ChatMember chatMember = new ChatMember(memberId);
        chatMemberRepository.save(chatMember);
        return chatMember;
    }
}
