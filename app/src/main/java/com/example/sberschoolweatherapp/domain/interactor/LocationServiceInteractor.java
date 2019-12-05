package com.example.sberschoolweatherapp.domain.interactor;

import com.example.sberschoolweatherapp.domain.model.CurrentPositionEntity;
import com.example.sberschoolweatherapp.domain.repository.ILocationServiceRepository;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public class LocationServiceInteractor implements ILocationServiceInteractor {

    final private ILocationServiceRepository mRepository;

    public LocationServiceInteractor(ILocationServiceRepository repository) {
        mRepository = repository;
    }

    @Override
    public void startLocationService() {
        mRepository.startLocationService();
    }

    @Override
    public void removeLocationUpdates() {
        mRepository.removeLocationUpdates();
    }

    @NotNull
    @Override
    public Observable<CurrentPositionEntity> getPosition() {
        return mRepository.getLocation() ;
    }
}
