package com.example.travelactivitytracker.mvc.service;

import com.example.travelactivitytracker.db.entity.Hotel;
import com.example.travelactivitytracker.db.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Iterable<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    @Transactional
    public void add(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @Override
    public void update(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @Override
    public Hotel getOne(long id) {
        return hotelRepository.findOne(id);
    }

    @Override
    public void delete(long id) {
        hotelRepository.delete(id);
    }
}
