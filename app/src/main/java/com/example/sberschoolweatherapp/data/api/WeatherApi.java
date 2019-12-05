package com.example.sberschoolweatherapp.data.api;


import com.example.sberschoolweatherapp.data.model.AllInfoWeb;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("weather")
    Observable<AllInfoWeb> getWeather(@Query("lat") String lat,
                                      @Query("lon") String lon,
                                      @Query("units") String units);
}
