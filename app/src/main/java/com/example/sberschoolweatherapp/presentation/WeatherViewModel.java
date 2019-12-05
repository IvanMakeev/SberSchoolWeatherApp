package com.example.sberschoolweatherapp.presentation;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sberschoolweatherapp.domain.interactor.ILocationServiceInteractor;
import com.example.sberschoolweatherapp.domain.interactor.IWeatherInteractor;
import com.example.sberschoolweatherapp.domain.model.CurrentLocationEntity;
import com.example.sberschoolweatherapp.domain.model.WeatherEntity;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class WeatherViewModel extends ViewModel {

    @NotNull
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    @NotNull
    private final IWeatherInteractor mWeatherInteractor;
    private ILocationServiceInteractor mLocationServiceInteractor;

    private MutableLiveData<WeatherEntity> mAllInfoMutableLiveData = new MutableLiveData<>();


    public WeatherViewModel(@NotNull IWeatherInteractor weatherInteractor, ILocationServiceInteractor locationServiceInteractor) {
        mWeatherInteractor = weatherInteractor;
        mLocationServiceInteractor = locationServiceInteractor;
    }

    public void showWeather() {
        mCompositeDisposable.add(mLocationServiceInteractor.getLocation()
                .flatMap((Function<CurrentLocationEntity, ObservableSource<WeatherEntity>>) location -> {
                    Log.d("TAG", Thread.currentThread().getName());
                    return mWeatherInteractor.getWeather(
                            String.valueOf(location.getLatitude()),
                            String.valueOf(location.getLongitude()))
                            .subscribeOn(Schedulers.io());
                })
//                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weather -> {
                            Log.d("TAG", "showWeather() onNext");
                            mAllInfoMutableLiveData.setValue(weather);
                        },
                        throwable -> {
                            Log.d("TAG", "showWeather() onError");
                            throwable.printStackTrace();
                        },
                        () -> {
                            Log.d("TAG", "showWeather() OnComplete");
                        }
                )
        );
    }

    public LiveData<WeatherEntity> getLiveData() {
        return mAllInfoMutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }
}
