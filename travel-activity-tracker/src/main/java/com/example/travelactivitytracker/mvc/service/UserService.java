package com.example.travelactivitytracker.mvc.service;


import com.example.travelactivitytracker.db.entity.User;

public interface UserService {
    Iterable<User> findAll();
    void add(User user);
    User findByUsername(String username);
    void delete(long id);
}
