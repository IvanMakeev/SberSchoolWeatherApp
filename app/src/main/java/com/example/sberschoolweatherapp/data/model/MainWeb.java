package com.example.sberschoolweatherapp.data.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class MainWeb {

    @NotNull
    @SerializedName("temp")
    private Double temp;

    @NotNull
    @SerializedName("temp_min")
    private Double tempMin;

    @NotNull
    @SerializedName("temp_max")
    private Double tempMax;

    public MainWeb(@NotNull Double temp,
                   @NotNull Double tempMin,
                   @NotNull Double tempMax) {
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
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

}
