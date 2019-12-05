package com.example.sberschoolweatherapp.data.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class MainWeb {

    @NotNull
    @SerializedName("temp")
    private Double temp;

    @NotNull
    @SerializedName("pressure")
    private Integer pressure;

    @NotNull
    @SerializedName("humidity")
    private Integer huIdity;

    @NotNull
    @SerializedName("temp_min")
    private Double tempMin;

    @NotNull
    @SerializedName("temp_max")
    private Double tempMax;

    public MainWeb(@NotNull Double temp,
                   @NotNull Integer pressure,
                   @NotNull Integer huIdity,
                   @NotNull Double tempMin,
                   @NotNull Double tempMax) {
        this.temp = temp;
        this.pressure = pressure;
        this.huIdity = huIdity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public Double getTemp() {
        return temp;
    }

    public Integer getPressure() {
        return pressure;
    }

    public Integer getHuIdity() {
        return huIdity;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

}
