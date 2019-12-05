package com.example.sberschoolweatherapp.data.repository;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.example.sberschoolweatherapp.App;
import com.example.sberschoolweatherapp.data.mapper.CurrentLocationMapper;
import com.example.sberschoolweatherapp.data.model.CurrentPosition;
import com.example.sberschoolweatherapp.domain.model.CurrentPositionEntity;
import com.example.sberschoolweatherapp.domain.repository.ILocationServiceRepository;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class LocationServiceRepository implements ILocationServiceRepository {

    private final MainLocationCallback mLocationCallback;
    private final CurrentLocationMapper mMapper;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationRequest mLocationRequest;


    public LocationServiceRepository() {
        Geocoder geocoder = new Geocoder(App.INSTANCE, Locale.getDefault());
        mLocationCallback = new MainLocationCallback(geocoder);
        mMapper = new CurrentLocationMapper();
    }

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
        }
    }

    @NotNull
    @Override
    public Observable<CurrentPositionEntity> getLocation() {
        return mLocationCallback
                .getSubject()
                .flatMap((Function<CurrentPosition, ObservableSource<CurrentPosition>>) currentLocation ->
                        Observable.fromCallable(() -> currentLocation))
                .map(mMapper::mapToEntity);
    }

    private LocationRequest getLocationRequest() {
        if (mLocationRequest == null) {
            mLocationRequest = LocationRequest.create();
            mLocationRequest.setInterval(15000L);
            mLocationRequest.setFastestInterval(7000L);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        }
        return mLocationRequest;
    }

    private static class MainLocationCallback extends LocationCallback {

        private Subject<CurrentPosition> mSubject = PublishSubject.create();
        private Geocoder mGeocoder;

        MainLocationCallback(Geocoder geocoder) {

            mGeocoder = geocoder;
        }

        @Override
        public void onLocationResult(LocationResult locationResult) {

            if (locationResult == null) {
                return;
            }

            for (Location location : locationResult.getLocations()) {
                List<Address> list = Collections.emptyList();
                try {
                    list = mGeocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Address address = null;
                if (!list.isEmpty()) {
                    address = list.get(0);
                }

                mSubject.onNext(new CurrentPosition(
                        location.getLatitude(),
                        location.getLongitude(),
                        address));
            }
        }

        @NotNull
        Subject<CurrentPosition> getSubject() {
            return mSubject;
        }
    }
}
