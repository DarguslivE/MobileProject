package com.mypackage.mobileproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class GeolocationActivity extends AppCompatActivity {

    TextView tvOut, tvLon, tvLat;
    LocationManager mlocManager;

    // слушатель: вызывается при каждом изменении координат
    LocationListener mlocListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d("GEO", location.getProvider() + ": "
                    + location.getLatitude() + ", " + location.getLongitude());
            tvLat.append("\n" + location.getLatitude());
            tvLon.append("\n" + location.getLongitude());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onProviderDisabled(String provider) {}
    };

    // запрос разрешений во время работы приложения
    private final ActivityResultLauncher<String[]> permissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
                    result -> {
                        if (!hasLocationPermission()) {
                            tvOut.setText("Нет разрешения на определение местоположения");
                        }
                        // если разрешение дали, подписку выполнит onResume
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocation);
        setTitle("Геолокация");

        tvOut = findViewById(R.id.textView1);
        tvLon = findViewById(R.id.longitude);
        tvLat = findViewById(R.id.latitude);

        // получаем сервис
        mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!hasLocationPermission()) {
            permissionLauncher.launch(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION});
        }
    }

    private boolean hasLocationPermission() {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean hasFinePermission() {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();
        if (!hasLocationPermission()) return;

        mlocManager.removeUpdates(mlocListener);   // на случай повторной подписки

        // подписываемся на изменения в показаниях датчика GPS
        boolean gpsOn = false;
        if (hasFinePermission()
                && mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mlocManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
            gpsOn = true;
        }
        // сеть даёт первую точку быстрее, особенно в помещении
        if (mlocManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            mlocManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 0, 0, mlocListener);
        }

        // если GPS включён, то ..., иначе вывести "GPS is not turned on..."
        if (gpsOn) {
            tvOut.setText("GPS is turned on...");
        } else {
            tvOut.setText("GPS is not turned on...");
        }
    }

    @Override
    protected void onPause() {
        mlocManager.removeUpdates(mlocListener);   // отписываемся, чтобы не тратить батарею
        super.onPause();
    }
}