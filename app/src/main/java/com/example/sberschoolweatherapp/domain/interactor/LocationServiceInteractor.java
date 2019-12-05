package com.example.sberschoolweatherapp.domain.interactor;

import com.example.sberschoolweatherapp.domain.model.CurrentLocationEntity;
import com.example.sberschoolweatherapp.domain.repository.ILocationServiceRepository;

import io.reactivex.Observable;
import io.reactivex.Single;

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

    @Override
    public Observable<CurrentLocationEntity> getLocation() {
        return mRepository.getLocation();
    }
}
