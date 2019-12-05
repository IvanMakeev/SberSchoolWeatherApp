package com.example.sberschoolweatherapp.domain.model;

import org.jetbrains.annotations.Nullable;

public class CurrentPositionEntity {

    private double latitude;
    private double longitude;
    @Nullable
    private final String mAddress;

    public CurrentPositionEntity(double latitude, double longitude, @Nullable String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.mAddress = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Nullable
    public String getAddress() {
        return mAddress;
    }
}
