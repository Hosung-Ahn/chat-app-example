package com.example.chatapp.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Getter
public class ChatMember {
    @Id
    private Long id;
    private Boolean active;
    private List<JoinedChatRoom> joinedChatRooms;

    public static class JoinedChatRoom {
        private String chatRoomId;
        private Date lastAccessTime;
    }
}
