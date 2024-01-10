package com.example.chatapp.domain.subDoc;

import lombok.Data;

import java.util.Date;

@Data
public class JoinedChatRoom {
    private String chatRoomId;
    private Date lastAccessTime;
}
