package com.example.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

class MyLocationListener implements LocationListener {

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Log.d("Location", "Latitude: " + location.getLatitude() + " Longitude: " + location.getLongitude());
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
        Log.d("Provider", provider + "status chnaged " + status);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
        Log.d("Provider", provider + "Enabled");
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
        Log.d("Provider", provider + "Disabled");
    }
}
