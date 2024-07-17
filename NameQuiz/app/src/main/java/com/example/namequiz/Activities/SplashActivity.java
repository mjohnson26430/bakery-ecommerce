package com.example.namequiz.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.namequiz.R;


public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 5000; // Splash screen delay in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        final Intent mainMenuIntent = new Intent(SplashActivity.this, MainMenu.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(mainMenuIntent);
                finish(); // Finish the SplashActivity to prevent it from being accessed again via the back button
            }
        }, SPLASH_DELAY);
    }
}
