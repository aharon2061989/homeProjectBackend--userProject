package com.UserSystem.UserSystem.service;

import com.UserSystem.UserSystem.model.User;
import com.UserSystem.UserSystem.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(User user) {
        userRepository.createUser(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteUserById(id);
    }

    @Override
    public User getUserById(Long id) throws JsonProcessingException {
        return userRepository.getUserById(id);
    }

    @Override
    public boolean registerUser(Long userId) throws JsonProcessingException {
        User user = getUserById(userId);
        if (user != null) {
            user.setRegistered(true);
            userRepository.updateUser(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkUserRegistration(Long userId) throws JsonProcessingException {
        User user = getUserById(userId);
        if (user != null) {
            return user.isRegistered();
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
