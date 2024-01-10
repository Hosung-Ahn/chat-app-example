package com.example.chatapp.service.dto;

import com.example.chatapp.domain.ChatMessage;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ChatRoomByMemberDto {
    private String title;
    private List<Long> joinedMemberIds;
    private List<ChatMessage> lastMessages;
    private Integer unreadCount;
}
