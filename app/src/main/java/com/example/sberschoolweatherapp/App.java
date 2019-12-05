package com.example.sberschoolweatherapp;

import android.app.Application;

import com.example.sberschoolweatherapp.data.api.ApiKeyInterceptor;
import com.example.sberschoolweatherapp.data.api.WeatherApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static WeatherApi mWeatherApi;
    public static App INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        OkHttpClient okHttpClient = provideClient();
        Retrofit retrofit = provideRetrofit(okHttpClient);
        mWeatherApi = provideApiService(retrofit);
    }

    public static WeatherApi getWeatherApi() {
        return mWeatherApi;
    }

    private OkHttpClient provideClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new ApiKeyInterceptor());
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.level(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        return builder.build();
    }

    private Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private WeatherApi provideApiService(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }
}
