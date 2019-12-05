package com.example.sberschoolweatherapp.data.mapper;

import com.example.sberschoolweatherapp.data.model.CurrentPosition;
import com.example.sberschoolweatherapp.domain.model.CurrentPositionEntity;

import org.jetbrains.annotations.NotNull;

public class CurrentLocationMapper implements IMapper<CurrentPositionEntity, CurrentPosition> {
    @NotNull
    @Override
    public CurrentPositionEntity mapToEntity(@NotNull CurrentPosition type) {
        return new CurrentPositionEntity(type.getLatitude(), type.getLongitude(), type.getAddress());
    }
}
