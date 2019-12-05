package com.example.sberschoolweatherapp.data.repository;

import com.example.sberschoolweatherapp.data.api.WeatherApi;
import com.example.sberschoolweatherapp.data.mapper.IMapper;
import com.example.sberschoolweatherapp.data.model.AllInfoWeb;
import com.example.sberschoolweatherapp.domain.model.WeatherEntity;
import com.example.sberschoolweatherapp.domain.repository.IWeatherRepository;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class WeatherRepository implements IWeatherRepository {

    private static final String METRIC = "metric";
    private final WeatherApi mApi;
    private final IMapper<WeatherEntity, AllInfoWeb> mMapper;

    public WeatherRepository(WeatherApi api, IMapper<WeatherEntity, AllInfoWeb> mapper) {
        mApi = api;
        mMapper = mapper;
    }

    @NotNull
    @Override
    public Observable<WeatherEntity> getWeather(@NotNull String lat, @NotNull String lon) {
        return mApi.getWeather(lat, lon, METRIC)
                .map(mMapper::mapToEntity);
    }
}
