package com.UserSystem.UserSystem.service;

import com.UserSystem.UserSystem.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserService {
    void createUser(User user);
    void updateUser(User user);
    void deleteUserById(Long id);
    User getUserById(Long id) throws JsonProcessingException;
    boolean registerUser(Long userId) throws JsonProcessingException;

    boolean checkUserRegistration(Long userId) throws JsonProcessingException;
}
