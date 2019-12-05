package com.example.sberschoolweatherapp.domain.model;

import android.location.Address;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InfoEntity {

    @NotNull
    private final WeatherEntity mWeatherEntity;

    @Nullable
    private Address mAddress;

    public InfoEntity(@NotNull WeatherEntity weatherEntity) {
        mWeatherEntity = weatherEntity;
    }

    @NotNull
    public WeatherEntity getWeatherEntity() {
        return mWeatherEntity;
    }

    @Nullable
    public Address getAddress() {
        return mAddress;
    }

    public void setAddress(@Nullable Address address) {
        mAddress = address;
    }
}
