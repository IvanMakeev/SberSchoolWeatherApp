package com.example.sberschoolweatherapp.data.mapper;

import com.example.sberschoolweatherapp.data.model.CurrentLocation;
import com.example.sberschoolweatherapp.domain.model.CurrentLocationEntity;

import org.jetbrains.annotations.NotNull;

public class CurrentLocationMapper implements IMapper<CurrentLocationEntity, CurrentLocation> {
    @NotNull
    @Override
    public CurrentLocationEntity mapToEntity(@NotNull CurrentLocation type) {
        return new CurrentLocationEntity(type.getLatitude(), type.getLongitude());
    }
}
