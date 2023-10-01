package com.UserSystem.UserSystem.repository;

import com.UserSystem.UserSystem.model.User;
import com.UserSystem.UserSystem.repository.mapper.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private static final String USER_TABLE_NAME = "user";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserMapper userMapper;


    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO " + USER_TABLE_NAME + " " + "(first_name, last_name, email, age, address) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAge(),
                user.getAddress()
        );
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE " + USER_TABLE_NAME + " SET first_name=?, last_name=?, email=?, age=?, address=?, joining_date=?, registered=? WHERE id=?";
        jdbcTemplate.update(
                sql,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAge(),
                user.getAddress(),
                user.getJoiningDate(),
                user.isRegistered(),
                user.getId()
        );
    }

    @Override
    public void deleteUserById(Long id) {
        String sql = "DELETE FROM " + USER_TABLE_NAME + " WHERE id=?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public User getUserById(Long id) throws JsonProcessingException {
        String sql = "SELECT * FROM " + USER_TABLE_NAME + " WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, userMapper, id);
        }
        catch (EmptyResultDataAccessException e){
            System.out.println("Empty Data Warning");
            return null;
        }
    }

    @Override
    public Boolean checkUserRegistration(Long userId) {
        String sql = "SELECT registered FROM " + USER_TABLE_NAME + " WHERE id=?";
        try {
            Boolean registered = jdbcTemplate.queryForObject(sql, Boolean.class, userId);
            return registered != null && registered;
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Empty Data Warning");
            return false;
        }
    }



}