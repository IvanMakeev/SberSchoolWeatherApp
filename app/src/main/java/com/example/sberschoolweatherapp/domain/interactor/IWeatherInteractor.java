package com.example.sberschoolweatherapp.domain.interactor;

import com.example.sberschoolweatherapp.domain.model.WeatherEntity;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface IWeatherInteractor {
    @NotNull
    Observable<WeatherEntity> getWeather(@NotNull String lat, @NotNull String lon);
}
