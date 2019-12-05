package com.example.sberschoolweatherapp.domain.interactor;

import com.example.sberschoolweatherapp.domain.model.CurrentLocationEntity;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface ILocationServiceInteractor {
    void startLocationService();

    void removeLocationUpdates();

    Observable<CurrentLocationEntity> getLocation();
}
