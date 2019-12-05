package com.example.sberschoolweatherapp.data.mapper;

import org.jetbrains.annotations.NotNull;

public interface IMapper<E, D> {

    @NotNull
    E mapToEntity(@NotNull D type);

}
