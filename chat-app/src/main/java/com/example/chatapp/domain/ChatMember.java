package com.example.chatapp.domain;

import com.example.chatapp.domain.subDoc.JoinedChatRoom;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
@Getter
public class ChatMember {
    @Id
    private Long id;
    private List<JoinedChatRoom> joinedChatRooms = new ArrayList<>();

    public ChatMember(Long id) {
        this.id = id;
    }
}
