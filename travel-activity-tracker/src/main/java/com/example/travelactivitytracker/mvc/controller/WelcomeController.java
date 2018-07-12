package com.example.travelactivitytracker.mvc.controller;

import com.example.travelactivitytracker.db.entity.Hotel;
import com.example.travelactivitytracker.db.entity.User;
import com.example.travelactivitytracker.db.repository.HotelRepository;
import com.example.travelactivitytracker.db.repository.UserRepository;
import com.example.travelactivitytracker.mvc.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class WelcomeController {

    private UserRepository userRepository;
    private HotelRepository hotelRepository;
    private Hotel hotel;
    private User user;

    @Autowired
    public WelcomeController (UserRepository userRepository, HotelRepository hotelRepository)
    {
        this.userRepository = userRepository;
        this.hotelRepository = hotelRepository;
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/add")
    public String addReservation(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "reservation";
    }

    @RequestMapping(value= "/welcome")
    public String welcome(Map<String, Object> model) {

        List<User> users = (List<User>) userRepository.findAll();
        model.put("users", users);

        List<Hotel> hotels = (List<Hotel>) hotelRepository.findAll();
        model.put("hotels", hotels);

        return "welcome";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Hotel dbHotel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User dbUser = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        user = userRepository.findByUsername(dbUser.getUsername());
        dbHotel.setUserId(user);
        try {
            hotel = dbHotel;
            hotelRepository.save(hotel);
        } catch (Exception e) {
            System.out.print(e);
        }
        return "redirect:/map";
    }

    @RequestMapping("/map")
    public String map(Map<String, Object> model) {
        model.put("startLat", user.getLatitude());
        model.put("startLong", user.getLongitude());
        model.put("endLat", hotel.getLatitude());
        model.put("endLong", hotel.getLongitude());
        model.put("name", hotel.getName());
        model.put("description", hotel.getDescription());
        return "map";
    }

}

