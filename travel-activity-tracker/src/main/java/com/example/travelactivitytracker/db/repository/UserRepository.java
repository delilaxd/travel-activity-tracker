package com.example.travelactivitytracker.db.repository;

import com.example.travelactivitytracker.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
