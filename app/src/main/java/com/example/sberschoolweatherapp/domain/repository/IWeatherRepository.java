package com.example.sberschoolweatherapp.domain.repository;

import com.example.sberschoolweatherapp.domain.model.WeatherEntity;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface IWeatherRepository {
    @NotNull
    Observable<WeatherEntity> getWeather(@NotNull String lat, @NotNull String lon);

}
