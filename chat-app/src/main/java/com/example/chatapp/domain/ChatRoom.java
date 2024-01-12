package com.example.chatapp.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {
    private String id;
    private String title;
    private List<Long> joinedMemberIds = new ArrayList<>();
    // 100개까지만 저장하고 그 이전 메세지의 경우 ChatMessage 를 커서 기반으로 페이징합니다.
    private List<ChatMessage> lastMessages = new ArrayList<>();



    public ChatRoom(String title) {
        this.title = title;
    }
}
