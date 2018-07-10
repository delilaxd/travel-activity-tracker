package com.example.travelactivitytracker.mvc.controller;

import com.example.travelactivitytracker.db.entity.Hotel;
import com.example.travelactivitytracker.db.entity.User;
import com.example.travelactivitytracker.db.repository.HotelRepository;
import com.example.travelactivitytracker.db.repository.UserRepository;
import com.example.travelactivitytracker.mvc.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    Hotel globalHotel = new Hotel();

    @RequestMapping(value = "/add")
    public String addReservation(Model model) {
        model.addAttribute("hotel", new Hotel ());
        return "reservation";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Hotel hotel) {
        User user = new User();
        user.setName("Mujo");
        user.setSurname("Mujic");
        user.setId(1);
        user.setLatitude(31.24967);
        user.setLongitude(30.06263);
        hotel.setUserId(user);
        hotel.setId(6);
        try {
            globalHotel = hotel;
            hotelRepository.save(hotel);

        } catch (Exception e) {
            System.out.print(e);
        }
        return "redirect:/map";
    }

    @RequestMapping("/map")
    public String map(Map<String, Object> model) {

        User user = new User();
        user.setId (1);
        user.setName("Mujo");
        user.setSurname("Mujic");
        user.setLatitude(37.776);
        user.setLongitude(-122.414);

        model.put("startLat", user.getLatitude());
        model.put("startLong", user.getLongitude());
        model.put("endLat", globalHotel.getLatitude());
        model.put("endLong", globalHotel.getLongitude());
        model.put("name", globalHotel.getName());
        model.put("description", globalHotel.getDescription());

        return "map";
    }

}

