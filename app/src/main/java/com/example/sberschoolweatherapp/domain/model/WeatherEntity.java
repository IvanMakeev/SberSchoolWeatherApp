package com.example.sberschoolweatherapp.domain.model;

import org.jetbrains.annotations.NotNull;

public class WeatherEntity {

    @NotNull
    private String description;

    @NotNull
    private Double temp;

    @NotNull
    private Double tempMin;

    @NotNull
    private Double tempMax;

    @NotNull
    private Double speed;

    public WeatherEntity(@NotNull String description,
                         @NotNull Double temp,
                         @NotNull Double tempMin,
                         @NotNull Double tempMax,
                         @NotNull Double speed) {
        this.description = description;
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.speed = speed;
    }

    public String getDescription() {
        return description;
    }

    @NotNull
    public Double getTemp() {
        return temp;
    }

    @NotNull
    public Double getTempMin() {
        return tempMin;
    }

    @NotNull
    public Double getTempMax() {
        return tempMax;
    }

    @NotNull
    public Double getSpeed() {
        return speed;
    }
}
