package com.example.travelactivitytracker.mvc.controller;

import com.example.travelactivitytracker.db.entity.User;
import com.example.travelactivitytracker.db.repository.UserRepository;
import com.example.travelactivitytracker.mvc.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        userRepository.save(user);
    }
}
