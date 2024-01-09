package com.example.chatapp.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
public class User {
    @Id
    private String id;

    private String nickname;
    private String email;
    private Status status;

    public void setStatus(Status status) {
        this.status = status;
    }
}
