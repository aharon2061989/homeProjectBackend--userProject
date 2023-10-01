package com.UserSystem.UserSystem.repository;

import com.UserSystem.UserSystem.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface UserRepository {
    void createUser(User user);
    void updateUser(User user);
    void deleteUserById(Long id);
    User getUserById(Long id)throws JsonProcessingException;
    Boolean checkUserRegistration(Long userId);
}
