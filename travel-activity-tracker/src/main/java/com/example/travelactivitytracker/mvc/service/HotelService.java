package com.example.travelactivitytracker.mvc.service;


import com.example.travelactivitytracker.db.entity.Hotel;
import com.example.travelactivitytracker.db.entity.User;

public interface HotelService {
    Iterable<Hotel> findAll();
    void add(Hotel hotel);
    public void update(Hotel hotel);
    public Hotel getOne(long id);
    public void delete(long id);
}
