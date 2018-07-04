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

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private HotelRepository hotelRepository;

    @Autowired
    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Hotel> findAllHotels() {
        return hotelRepository.findAll();
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
