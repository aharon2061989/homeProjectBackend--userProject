package com.UserSystem.UserSystem.controller;

import com.UserSystem.UserSystem.model.User;
import com.UserSystem.UserSystem.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public void  createUser(@RequestBody User user){
        logger.info("user " + user.getFirstName() + " " + user.getLastName() + " created successfully.");
        userService.createUser(user);
    }

    @PutMapping("/update/{id}")
    public void updateUser(User user){
        logger.info("user " + user.getFirstName() + " " + user.getLastName() + " updated successfully.");
        userService.updateUser(user);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUserById(@PathVariable Long userId){
        logger.info("user deleted successfully.");
        userService.deleteUserById(userId);
    }

    @GetMapping("/getUser/{userId}")
    public User getUserById(@PathVariable Long userId)throws JsonProcessingException {
        return userService.getUserById(userId);
    }

    @PostMapping("/registerUser/{id}")
    public ResponseEntity<String> registerUser(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            if (user != null) {
                user.setRegistered(true);
                userService.updateUser(user);
                return ResponseEntity.ok("User registered successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User registration failed: User not found.");
            }
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during user registration.");
        }
    }

    @GetMapping("/checkRegistration/{userId}")
    public ResponseEntity<Boolean> checkUserRegistration(@PathVariable Long userId) throws JsonProcessingException {
        Boolean isRegistered = userService.checkUserRegistration(userId);
        if (isRegistered != null) {
            return ResponseEntity.ok(isRegistered);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
