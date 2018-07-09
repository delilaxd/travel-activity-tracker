package com.example.travelactivitytracker.mvc.controller;

import com.example.travelactivitytracker.db.entity.Hotel;
import com.example.travelactivitytracker.db.repository.HotelRepository;
import com.example.travelactivitytracker.mvc.dto.HotelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private HotelRepository hotelRepository;

    @Autowired
    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @RequestMapping ("/all")
    public String hotel (Map<String, Object> model)
    {
        List<Hotel> hotels = (List<Hotel>) hotelRepository.findAll();
        model.put("hotels", hotels);
        return "hotel";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addHotel(@RequestBody HotelDto hotelDto) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDto.getName());
        hotel.setDescription(hotelDto.getDescription());
        hotel.setAddress(hotelDto.getAddress());
        hotel.setLatitude(hotelDto.getLatitude());
        hotel.setLongitude(hotelDto.getLongitude());
        hotelRepository.save(hotel);
    }
}
