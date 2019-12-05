package com.example.sberschoolweatherapp.domain.repository;

import com.example.sberschoolweatherapp.domain.model.CurrentPositionEntity;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public interface ILocationServiceRepository {

    void startLocationService();

    void removeLocationUpdates();

    @NotNull
    Observable<CurrentPositionEntity> getLocation();

}
