package com.example.sberschoolweatherapp.data.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class WindWeb {

    @NotNull
    @SerializedName("speed")
    private Double speed;

    @NotNull
    @SerializedName("deg")
    private Double deg;

    public WindWeb(@NotNull Double speed, @NotNull Double deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public Double getSpeed() {
        return speed;
    }

    public Double getDeg() {
        return deg;
    }

}
