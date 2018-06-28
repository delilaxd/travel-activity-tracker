package com.example.travelactivitytracker.repository;

import com.example.travelactivitytracker.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository  extends JpaRepository<Hotel, Long> {
}
