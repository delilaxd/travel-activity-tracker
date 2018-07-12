package com.example.travelactivitytracker.mvc.service;

import com.example.travelactivitytracker.db.entity.User;
import com.example.travelactivitytracker.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void add(User user) {
        User appuser = userRepository.findByUsername(user.getUsername());

        if(appuser == null)
            userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }
}
