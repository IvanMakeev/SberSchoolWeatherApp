package com.example.sberschoolweatherapp.data.repository;

import com.example.sberschoolweatherapp.data.api.WeatherApi;
import com.example.sberschoolweatherapp.data.mapper.IMapper;
import com.example.sberschoolweatherapp.data.model.AllInfoWeb;
import com.example.sberschoolweatherapp.domain.model.InfoEntity;
import com.example.sberschoolweatherapp.domain.repository.IInfoRepository;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public class InfoRepository implements IInfoRepository {

    private static final String METRIC = "metric";
    @NotNull
    private final WeatherApi mApi;
    @NotNull
    private final IMapper<InfoEntity, AllInfoWeb> mMapper;

    public InfoRepository(@NotNull WeatherApi api, @NotNull IMapper<InfoEntity, AllInfoWeb> mapper) {
        mApi = api;
        mMapper = mapper;
    }

    @NotNull
    @Override
    public Observable<InfoEntity> getInfo(@NotNull String lat, @NotNull String lon) {
        return mApi.getWeather(lat, lon, METRIC)
                .map(mMapper::mapToEntity);
    }
}
