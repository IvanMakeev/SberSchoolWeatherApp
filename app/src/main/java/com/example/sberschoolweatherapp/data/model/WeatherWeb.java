package com.example.sberschoolweatherapp.data.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class WeatherWeb {

    @NotNull
    @SerializedName("description")
    private String description;

    public WeatherWeb(@NotNull String description) {
        this.description = description;
    }

    @NotNull
    public String getDescription() {
        return description;
    }
}
