package com.example.sberschoolweatherapp.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sberschoolweatherapp.domain.interactor.ILocationServiceInteractor;
import com.example.sberschoolweatherapp.domain.interactor.IInfoInteractor;
import com.example.sberschoolweatherapp.domain.model.CurrentPositionEntity;
import com.example.sberschoolweatherapp.domain.model.InfoEntity;

import org.jetbrains.annotations.NotNull;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class WeatherViewModel extends ViewModel {

    @NotNull
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    @NotNull
    private final IInfoInteractor mWeatherInteractor;
    private ILocationServiceInteractor mLocationServiceInteractor;

    private MutableLiveData<InfoEntity> mAllInfoMutableLiveData = new MutableLiveData<>();


    public WeatherViewModel(@NotNull IInfoInteractor weatherInteractor, ILocationServiceInteractor locationServiceInteractor) {
        mWeatherInteractor = weatherInteractor;
        mLocationServiceInteractor = locationServiceInteractor;
    }

    public void showWeather() {
        mCompositeDisposable.add(mLocationServiceInteractor.getPosition()
                .subscribeOn(Schedulers.io())
                .flatMap((Function<CurrentPositionEntity, ObservableSource<InfoEntity>>) position ->
                        mWeatherInteractor.getInfo(
                                String.valueOf(position.getLatitude()),
                                String.valueOf(position.getLongitude()))
                                .map(info -> {
                                    info.setAddress(position.getAddress());
                                    return info;
                                }))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        info -> mAllInfoMutableLiveData.setValue(info),
                        Throwable::printStackTrace
                )
        );
    }

    public LiveData<InfoEntity> getLiveData() {
        return mAllInfoMutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }
}
