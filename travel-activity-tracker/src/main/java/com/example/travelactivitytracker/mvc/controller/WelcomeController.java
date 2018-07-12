package com.example.travelactivitytracker.mvc.controller;

import com.example.travelactivitytracker.db.entity.Hotel;
import com.example.travelactivitytracker.db.entity.User;
import com.example.travelactivitytracker.db.repository.HotelRepository;
import com.example.travelactivitytracker.db.repository.UserRepository;
import com.example.travelactivitytracker.mvc.service.HotelService;
import com.example.travelactivitytracker.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class WelcomeController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;

    private Hotel hotel;
    private User user;

    @Autowired
    public WelcomeController(UserRepository userRepository, HotelRepository hotelRepository) {
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

    @RequestMapping(value = "/welcome")
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

    @RequestMapping(value = "/admin")
    public String home(Map<String, Object> model) {
        List<User> users = (List<User>) userRepository.findAll();
        model.put("users", users);
        List<Hotel> hotels = (List<Hotel>) hotelRepository.findAll();
        model.put("hotels", hotels);
        return "admin";
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(@RequestParam(value = "id") long id,
                             @RequestParam(value = "username") String username,
                             @RequestParam(value = "longitude") double longitude,
                             @RequestParam(value = "latitude") double latitude, Model model) {
        userService.update(new User(id, username, longitude, latitude));

        return "redirect:/admin";
    }


    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.GET)
    public String updateUserView(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userRepository.getOne(id));
        return "updateUser";
    }

    @RequestMapping(value = "/updateHotel", method = RequestMethod.POST)
    public String updateHotel(@RequestParam(value = "id") long id,
                              @RequestParam(value = "address") String address,
                              @RequestParam(value = "description") String description,
                              @RequestParam(value = "longitude") double longitude,
                              @RequestParam(value = "latitude") double latitude,
                              @RequestParam(value = "name") String name, Model model) {
        hotelService.update(new Hotel(id, address, description, longitude, latitude, name));

        return "redirect:/admin";
    }


    @RequestMapping(value = "/updateHotel/{id}", method = RequestMethod.GET)
    public String updateHotelView(@PathVariable("id") long id, Model model) {
        model.addAttribute("hotel", hotelRepository.getOne(id));
        return "updateHotel";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String deleteUser(@RequestParam(value = "id") long id, Model model) {

        userService.delete(id);

        return "redirect:/admin";
    }

    @RequestMapping(value = "/deleteHotel", method = RequestMethod.POST)
    public String deleteHotel(@RequestParam(value = "id") long id, Model model) {

        hotelService.delete(id);

        return "redirect:/admin";
    }

}

