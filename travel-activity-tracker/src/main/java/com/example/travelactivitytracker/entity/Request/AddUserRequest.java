package com.example.travelactivitytracker.entity.Request;

import com.example.travelactivitytracker.entity.Hotel;

import java.util.HashSet;
import java.util.Set;

public class AddUserRequest {

    private String name;
    private String surname;
    private double latitude;
    private double longitude;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
