package com.example.sberschoolweatherapp.data.mapper;

import com.example.sberschoolweatherapp.data.model.AllInfoWeb;
import com.example.sberschoolweatherapp.domain.model.InfoEntity;
import com.example.sberschoolweatherapp.domain.model.WeatherEntity;

import org.jetbrains.annotations.NotNull;

public class WeatherMapper implements IMapper<InfoEntity, AllInfoWeb> {

    @NotNull
    @Override
    public InfoEntity mapToEntity(@NotNull AllInfoWeb type) {

        WeatherEntity weatherEntity = new WeatherEntity(
                type.getWeather().get(0).getDescription(),
                type.getMain().getTemp(),
                type.getMain().getTempMin(),
                type.getMain().getTempMax(),
                type.getWind().getSpeed()
        );
        return new InfoEntity(weatherEntity);
    }
}
