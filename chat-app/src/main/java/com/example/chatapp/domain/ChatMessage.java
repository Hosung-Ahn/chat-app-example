package com.example.chatapp.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter
public class ChatMessage {
    @Id
    private String id;
    private String chatRoomId;
    private String senderId;
    private Date sendTime;
    private String content;
}
