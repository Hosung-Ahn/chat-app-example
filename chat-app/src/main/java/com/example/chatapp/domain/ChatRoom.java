package com.example.chatapp.domain;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
public class ChatRoom {
    private String id;
    private String title;
    private List<String> joinedMemberIds = new ArrayList<>();
    // 100개까지만 저장하고 그 이전 메세지의 경우 ChatMessage 를 커서 기반으로 페이징합니다.
    private List<ChatMessage> lastMessages = new ArrayList<>();
}
