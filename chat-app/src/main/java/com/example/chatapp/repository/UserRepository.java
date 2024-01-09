package com.example.chatapp.repository;

import com.example.chatapp.domain.Status;
import com.example.chatapp.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);
}
