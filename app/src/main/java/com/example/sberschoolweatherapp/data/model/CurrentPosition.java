package com.example.sberschoolweatherapp.data.model;

import android.location.Address;

import org.jetbrains.annotations.Nullable;

public class CurrentPosition {

    private final double latitude;
    private final double longitude;
    @Nullable
    private final Address mAddress;

    public CurrentPosition(double latitude, double longitude, @Nullable Address address) {
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
