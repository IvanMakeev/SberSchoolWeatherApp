package com.example.sberschoolweatherapp.domain.repository;

import com.example.sberschoolweatherapp.domain.model.CurrentLocationEntity;

import io.reactivex.Observable;

public interface ILocationServiceRepository {

    void startLocationService();

    void removeLocationUpdates();

    Observable<CurrentLocationEntity> getLocation();

}
