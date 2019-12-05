package com.example.sberschoolweatherapp.data.api;




import com.example.sberschoolweatherapp.BuildConfig;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {

    private static final String PROPERTY_API_KEY = "appid";

    @Override
    public @NotNull
    Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl httpUrl = request
                .url()
                .newBuilder()
                .addQueryParameter(PROPERTY_API_KEY, BuildConfig.API_KEY)
                .build();
        return chain.proceed(request
                .newBuilder()
                .url(httpUrl)
                .build());
    }
}
