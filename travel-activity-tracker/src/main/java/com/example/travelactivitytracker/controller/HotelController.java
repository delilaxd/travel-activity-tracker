package com.example.travelactivitytracker.controller;

import com.example.travelactivitytracker.entity.Hotel;
import com.example.travelactivitytracker.entity.Request.AddHotelRequest;
import com.example.travelactivitytracker.repository.HotelRepository;
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
    public HotelController(HotelRepository hotelRepository)
    {
        this.hotelRepository = hotelRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Hotel> findAllHotels()
    {
        return hotelRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addHotel (@RequestBody AddHotelRequest addHotelRequest)
    {
        Hotel hotel = new Hotel();
        hotel.setName(addHotelRequest.getName());
        hotel.setDescription(addHotelRequest.getDescription());
        hotel.setAddress(addHotelRequest.getAddress());
        hotel.setLatitude(addHotelRequest.getLatitude());
        hotel.setLongitude(addHotelRequest.getLongitude());
        hotelRepository.save(hotel);

    }
}
