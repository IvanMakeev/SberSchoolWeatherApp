package com.example.sberschoolweatherapp.presentation;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.sberschoolweatherapp.domain.interactor.ILocationServiceInteractor;
import com.example.sberschoolweatherapp.domain.interactor.IWeatherInteractor;

import org.jetbrains.annotations.NotNull;


public class WeatherViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NotNull
    private final IWeatherInteractor mWeatherInteractor;
    private ILocationServiceInteractor mLocationServiceInteractor;

    public WeatherViewModelFactory(@NotNull IWeatherInteractor weatherInteractor, ILocationServiceInteractor locationServiceInteractor) {
        mWeatherInteractor = weatherInteractor;
        mLocationServiceInteractor = locationServiceInteractor;
    }

    @SuppressWarnings("unchecked cast")
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        return (T) new WeatherViewModel(mWeatherInteractor, mLocationServiceInteractor);
    }
}
