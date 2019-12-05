package com.example.sberschoolweatherapp.domain.interactor;

import com.example.sberschoolweatherapp.domain.model.InfoEntity;
import com.example.sberschoolweatherapp.domain.repository.IInfoRepository;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;

public class InfoInteractor implements IInfoInteractor {

    @NotNull
    private final IInfoRepository mRepository;

    public InfoInteractor(@NotNull IInfoRepository repository) {
        mRepository = repository;
    }

    @NotNull
    @Override
    public Observable<InfoEntity> getInfo(@NotNull String lat, @NotNull String lon) {
        return mRepository.getInfo(lat, lon);
    }
}
