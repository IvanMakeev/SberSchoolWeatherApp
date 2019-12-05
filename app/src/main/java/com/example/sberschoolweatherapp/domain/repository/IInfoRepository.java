package com.example.sberschoolweatherapp.domain.repository;

import com.example.sberschoolweatherapp.domain.model.InfoEntity;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public interface IInfoRepository {
    @NotNull
    Observable<InfoEntity> getInfo(@NotNull String lat, @NotNull String lon);

}
