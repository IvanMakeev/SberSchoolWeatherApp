package com.example.sberschoolweatherapp.data.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AllInfoWeb {

    @NotNull
    @SerializedName("weather")
    private List<WeatherWeb> weather;

    @NotNull
    @SerializedName("base")
    private String base;

    @NotNull
    @SerializedName("main")
    private MainWeb main;

    @NotNull
    @SerializedName("wind")
    private WindWeb wind;

    public AllInfoWeb(
            @NotNull List<WeatherWeb> weather,
            @NotNull String base,
            @NotNull MainWeb main,
            @NotNull WindWeb wind) {
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.wind = wind;
    }

    public List<WeatherWeb> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public MainWeb getMain() {
        return main;
    }

    public WindWeb getWind() {
        return wind;
    }


}