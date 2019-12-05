package com.example.sberschoolweatherapp.data.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AllInfoWeb {

    @NotNull
    @SerializedName("weather")
    private List<WeatherWeb> weather;

    @NotNull
    @SerializedName("main")
    private MainWeb main;

    @NotNull
    @SerializedName("wind")
    private WindWeb wind;

    public AllInfoWeb(
            @NotNull List<WeatherWeb> weather,
            @NotNull MainWeb main,
            @NotNull WindWeb wind) {
        this.weather = weather;
        this.main = main;
        this.wind = wind;
    }

    @NotNull
    public List<WeatherWeb> getWeather() {
        return weather;
    }

    @NotNull
    public MainWeb getMain() {
        return main;
    }

    @NotNull
    public WindWeb getWind() {
        return wind;
    }


}