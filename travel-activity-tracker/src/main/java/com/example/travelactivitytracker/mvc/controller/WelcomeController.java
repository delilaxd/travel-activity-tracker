package com.example.travelactivitytracker.mvc.controller;

import com.example.travelactivitytracker.db.entity.Hotel;
import com.example.travelactivitytracker.db.entity.User;
import com.example.travelactivitytracker.db.repository.HotelRepository;
import com.example.travelactivitytracker.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class WelcomeController {

    private UserRepository userRepository;
    private HotelRepository hotelRepository;

    @Autowired
    public WelcomeController (UserRepository userRepository, HotelRepository hotelRepository)
    {
        this.userRepository = userRepository;
        this.hotelRepository = hotelRepository;
    }

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {

        List<User> users = (List<User>) userRepository.findAll();
        model.put("users", users);

        List<Hotel> hotels = (List<Hotel>) hotelRepository.findAll();
        model.put("hotels", hotels);

        return "welcome";
    }
}
