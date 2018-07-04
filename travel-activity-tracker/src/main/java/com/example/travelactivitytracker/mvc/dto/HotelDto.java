package com.example.travelactivitytracker.mvc.dto;

public class HotelDto {

    private String name;

    private String description;

    private long latitude;

    private long longitude;

    private String address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String location) {
        this.address = address;
    }


}