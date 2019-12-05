package com.example.sberschoolweatherapp.domain.model;

public class CurrentLocationEntity {

    private double latitude;

    private double longitude;

    public CurrentLocationEntity(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
