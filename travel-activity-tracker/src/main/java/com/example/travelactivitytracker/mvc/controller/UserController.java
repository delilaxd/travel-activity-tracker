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
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping ("/all")
    public String user (Map<String, Object> model)
    {
        List<User> users = (List<User>) userRepository.findAll();
        model.put("users", users);
        return "user";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        userRepository.save(user);
    }
}
