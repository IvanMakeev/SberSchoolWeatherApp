package com.example.sberschoolweatherapp.data.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class WindWeb {

    @NotNull
    @SerializedName("speed")
    private Double speed;

    public WindWeb(@NotNull Double speed) {
        this.speed = speed;
    }

    @NotNull
    public Double getSpeed() {
        return speed;
    }

}
