package com.example.sberschoolweatherapp.data.mapper;

import com.example.sberschoolweatherapp.data.model.AllInfoWeb;
import com.example.sberschoolweatherapp.domain.model.WeatherEntity;

import org.jetbrains.annotations.NotNull;

public class WeatherMapper implements IMapper<WeatherEntity, AllInfoWeb> {

    @NotNull
    @Override
    public WeatherEntity mapToEntity(@NotNull AllInfoWeb type) {

        return new WeatherEntity(
                type.getWeather().get(0).getDescription(),
                type.getMain().getTemp(),
                type.getMain().getTempMin(),
                type.getMain().getTempMax(),
                type.getWind().getSpeed()
        );
    }
}
