package com.example.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    int REQUEST_CODE = 101;
    TextView textView;
    Button btnGetLocation;

    private LocationManager locationManager;
    private MyLocationListener myLocationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        btnGetLocation = findViewById(R.id.btnGetLocation);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE);
        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        myLocationListener = new MyLocationListener();
        final Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        btnGetLocation.setOnClickListener(view -> {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, myLocationListener);
            if (location != null){
                Double latitude = location.getLatitude();
                Double longitude = location.getLongitude();
                Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                List<Address> addresses = null;
                try {
                    addresses = geocoder.getFromLocation(latitude, longitude, 1);
                }catch (IOException e){
                    Log.d("Exception", e.getMessage());
                }
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                textView.setText(city + "\n" + state + "\n" + country);
            } else {
                Toast.makeText(MainActivity.this, "Please enable Location", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
