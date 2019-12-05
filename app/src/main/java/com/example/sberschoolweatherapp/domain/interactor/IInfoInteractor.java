package com.example.sberschoolweatherapp.domain.interactor;

import com.example.sberschoolweatherapp.domain.model.InfoEntity;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public interface IInfoInteractor {
    @NotNull
    Observable<InfoEntity> getInfo(@NotNull String lat, @NotNull String lon);
}
