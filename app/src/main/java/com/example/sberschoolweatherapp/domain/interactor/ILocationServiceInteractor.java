package com.example.sberschoolweatherapp.domain.interactor;

import com.example.sberschoolweatherapp.domain.model.CurrentPositionEntity;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public interface ILocationServiceInteractor {
    void startLocationService();

    void removeLocationUpdates();

    @NotNull
    Observable<CurrentPositionEntity> getPosition();
}
