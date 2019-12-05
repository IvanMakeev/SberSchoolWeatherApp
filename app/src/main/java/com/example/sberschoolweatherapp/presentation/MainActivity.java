package com.example.sberschoolweatherapp.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sberschoolweatherapp.App;
import com.example.sberschoolweatherapp.R;
import com.example.sberschoolweatherapp.data.api.WeatherApi;
import com.example.sberschoolweatherapp.data.mapper.IMapper;
import com.example.sberschoolweatherapp.data.mapper.WeatherMapper;
import com.example.sberschoolweatherapp.data.model.AllInfoWeb;
import com.example.sberschoolweatherapp.data.repository.LocationServiceRepository;
import com.example.sberschoolweatherapp.data.repository.WeatherRepository;
import com.example.sberschoolweatherapp.domain.interactor.ILocationServiceInteractor;
import com.example.sberschoolweatherapp.domain.interactor.IWeatherInteractor;
import com.example.sberschoolweatherapp.domain.interactor.LocationServiceInteractor;
import com.example.sberschoolweatherapp.domain.interactor.WeatherInteractor;
import com.example.sberschoolweatherapp.domain.model.WeatherEntity;
import com.example.sberschoolweatherapp.domain.repository.ILocationServiceRepository;
import com.example.sberschoolweatherapp.domain.repository.IWeatherRepository;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 101;
    private static final int REQUEST_CHECK_SETTINGS = 102;

    @NotNull
    private WeatherApi mApi = App.getWeatherApi();
    @NotNull
    private IMapper<WeatherEntity, AllInfoWeb> mMapper = new WeatherMapper();
    @NotNull
    private IWeatherRepository mWeatherRepository = new WeatherRepository(mApi, mMapper);
    @NotNull
    private IWeatherInteractor mWeatherInteractor = new WeatherInteractor(mWeatherRepository);
    @NotNull
    private ILocationServiceRepository mLocationServiceRepository = new LocationServiceRepository();
    @NotNull
    private ILocationServiceInteractor mLocationServiceInteractor = new LocationServiceInteractor(mLocationServiceRepository);

    private WeatherViewModel mWeatherViewModel;


//    private FusedLocationProviderClient mFusedLocationProviderClient;

//    private LocationCallback mLocationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WeatherViewModelFactory factory = new WeatherViewModelFactory(mWeatherInteractor, mLocationServiceInteractor);
        mWeatherViewModel = ViewModelProviders.of(this, factory).get(WeatherViewModel.class);
        initView();

        mWeatherViewModel.showWeather();
//        mLocationCallback = new MainLocationCallback(weatherViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mWeatherViewModel.showWeather();
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkGooglePlayServices();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationServiceInteractor.removeLocationUpdates();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 1) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    mLocationServiceInteractor.startLocationService();
                } else {
                    finish();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        mLocationServiceInteractor.startLocationService();
                        break;
                    case Activity.RESULT_CANCELED:
                        finish();
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    private void initView() {
        TextView currentWeather = findViewById(R.id.current_weather);
        TextView temp = findViewById(R.id.temp);
        TextView minTemp = findViewById(R.id.min_temp);
        TextView maxTemp = findViewById(R.id.max_temp);
        TextView windSpeed = findViewById(R.id.wind_speed);

        mWeatherViewModel.getLiveData().observe(this, weather -> {
            currentWeather.setText(weather.getDescription());
            temp.setText(String.valueOf(weather.getTemp()));
            minTemp.setText(String.valueOf(weather.getTempMin()));
            maxTemp.setText(String.valueOf(weather.getTempMax()));
            windSpeed.setText(String.valueOf(weather.getSpeed()));
        });
    }

    private void checkGooglePlayServices() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();

        int statusCode = googleApiAvailability.isGooglePlayServicesAvailable(this);

        if (statusCode != ConnectionResult.SUCCESS) {
            Dialog errorDialog = googleApiAvailability.getErrorDialog(this, statusCode,
                    0, dialogInterface -> finish());

            errorDialog.show();
        } else {
            checkPermission();
        }
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        } else {
            checkDeviceSettings();
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_CODE);
    }

    private void checkDeviceSettings() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(getLocationRequest());

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(this, locationSettingsResponse -> {
            mLocationServiceInteractor.startLocationService();
        });

        task.addOnFailureListener(this, e -> {
            if (e instanceof ResolvableApiException) {
                try {
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    resolvable.startResolutionForResult(MainActivity.this,
                            REQUEST_CHECK_SETTINGS);
                } catch (IntentSender.SendIntentException sendEx) {
                    // Ignore the error.
                }
            }
        });
    }

    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(30000L);
        locationRequest.setFastestInterval(15000L);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        return locationRequest;
    }

    //    @SuppressLint("MissingPermission")
//    private void startLocationService() {
//        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//
//        mFusedLocationProviderClient.requestLocationUpdates(getLocationRequest(), mLocationCallback, null);
//    }

//    private static class MainLocationCallback extends LocationCallback {
//
//        private final WeatherViewModel mWeatherViewModel;
//
//        private MainLocationCallback(WeatherViewModel weatherViewModel) {
//            mWeatherViewModel = weatherViewModel;
//        }
//
//        @Override
//        public void onLocationResult(LocationResult locationResult) {
//            Log.d(TAG, "onLocationResult() called with: locationResult = [" + locationResult + "]");
//
//            if (locationResult == null) {
//                return;
//            }
//
//            for (Location location : locationResult.getLocations()) {
//                Log.i(TAG, "Location from LocationResuls = " + location);
//                location.getLatitude();
//                location.getLongitude();
//
//                mWeatherViewModel.showWeather(location.getLatitude(), location.getLongitude());
//            }
//        }
//    }

}
