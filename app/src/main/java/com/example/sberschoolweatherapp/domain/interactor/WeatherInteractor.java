package com.example.sberschoolweatherapp.domain.interactor;

import com.example.sberschoolweatherapp.domain.model.WeatherEntity;
import com.example.sberschoolweatherapp.domain.repository.IWeatherRepository;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.Single;

public class WeatherInteractor implements IWeatherInteractor {

    private final IWeatherRepository mRepository;

    public WeatherInteractor(IWeatherRepository repository) {
        mRepository = repository;
    }

    @NotNull
    @Override
    public Observable<WeatherEntity> getWeather(@NotNull String lat, @NotNull String lon) {
        return mRepository.getWeather(lat, lon);
    }
}
