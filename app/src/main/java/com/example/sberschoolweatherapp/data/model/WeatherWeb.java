package com.example.sberschoolweatherapp.data.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class WeatherWeb {

    @NotNull
    @SerializedName("id")
    private Integer id;

    @NotNull
    @SerializedName("main")
    private String main;

    @NotNull
    @SerializedName("description")
    private String description;

    @NotNull
    @SerializedName("icon")
    private String icon;

    public WeatherWeb(@NotNull Integer id, @NotNull String main, @NotNull String description, @NotNull String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

}
