package com.example.sberschoolweatherapp.data.model;

import org.jetbrains.annotations.Nullable;

public class CurrentPosition {

    private final double latitude;
    private final double longitude;
    @Nullable
    private final String mAddress;

    public CurrentPosition(double latitude, double longitude, @Nullable String address) {
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
