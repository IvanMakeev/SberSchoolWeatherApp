package com.example.sberschoolweatherapp.domain.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InfoEntity {

    @NotNull
    private final WeatherEntity mWeatherEntity;

    @Nullable
    private String mAddress;

    public InfoEntity(@NotNull WeatherEntity weatherEntity) {
        mWeatherEntity = weatherEntity;
    }

    @NotNull
    public WeatherEntity getWeatherEntity() {
        return mWeatherEntity;
    }

    @Nullable
    public String getAddress() {
        return mAddress;
    }

    public void setAddress(@Nullable String address) {
        mAddress = address;
    }
}
