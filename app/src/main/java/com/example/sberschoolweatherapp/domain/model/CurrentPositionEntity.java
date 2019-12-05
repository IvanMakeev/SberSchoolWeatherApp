package com.example.sberschoolweatherapp.domain.model;

import android.location.Address;

import org.jetbrains.annotations.Nullable;

public class CurrentPositionEntity {

    private double latitude;
    private double longitude;
    @Nullable
    private final Address mAddress;

    public CurrentPositionEntity(double latitude, double longitude, @Nullable Address address) {
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
    public Address getAddress() {
        return mAddress;
    }
}
