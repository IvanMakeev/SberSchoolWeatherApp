package com.example.sberschoolweatherapp.data.repository;

import android.annotation.SuppressLint;
import android.location.Location;
import android.util.Log;

import com.example.sberschoolweatherapp.App;
import com.example.sberschoolweatherapp.data.mapper.CurrentLocationMapper;
import com.example.sberschoolweatherapp.data.model.CurrentLocation;
import com.example.sberschoolweatherapp.domain.model.CurrentLocationEntity;
import com.example.sberschoolweatherapp.domain.repository.ILocationServiceRepository;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class LocationServiceRepository implements ILocationServiceRepository {

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private MainLocationCallback mLocationCallback = new MainLocationCallback();
    private LocationRequest mLocationRequest;

    @SuppressLint("MissingPermission")
    @Override
    public void startLocationService() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(App.INSTANCE);
        mFusedLocationProviderClient.requestLocationUpdates(getLocationRequest(), mLocationCallback, null);
    }

    @Override
    public void removeLocationUpdates() {
        if (mFusedLocationProviderClient != null) {
            mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
            Log.d("TAG", "removeLocationUpdates() called");
        }
    }

    @Override
    public Observable<CurrentLocationEntity> getLocation() {
        return mLocationCallback
                .getSubject()
                .flatMap((Function<CurrentLocation, ObservableSource<CurrentLocation>>) currentLocation ->
                        Observable.fromCallable(() -> currentLocation))
                .map(currentLocation -> {
                    CurrentLocationMapper mapper = new CurrentLocationMapper();
                    return mapper.mapToEntity(currentLocation);
                });
    }

    private LocationRequest getLocationRequest() {
        if (mLocationRequest == null) {
            mLocationRequest = LocationRequest.create();
            mLocationRequest.setInterval(30000L);
            mLocationRequest.setFastestInterval(15000L);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        }
        return mLocationRequest;
    }

    private static class MainLocationCallback extends LocationCallback {

        private Subject<CurrentLocation> mSubject = PublishSubject.create();

        @Override
        public void onLocationResult(LocationResult locationResult) {

            if (locationResult == null) {
                return;
            }

            for (Location location : locationResult.getLocations()) {
                mSubject.onNext(new CurrentLocation(
                        location.getLatitude(),
                        location.getLongitude()
                ));
            }
        }

        public Subject<CurrentLocation> getSubject() {
            return mSubject;
        }
    }
}
