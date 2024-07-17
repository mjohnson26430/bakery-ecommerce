package com.example.namequiz.Activities;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.namequiz.R;

public class AudioActivity extends AppCompatActivity {


    private static MediaPlayer backgroundMusic;


    private static final String VOLUME_PROGRESS_KEY = "volume_progress";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound_activity);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        if (backgroundMusic == null || !backgroundMusic.isPlaying()) {
            backgroundMusic = MediaPlayer.create(this, R.raw.aloelite);
            backgroundMusic.setLooping(true);
            backgroundMusic.start();
        }



        Button onButton = findViewById(R.id.on);
        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Resume background music if it's paused
                if (backgroundMusic != null && !backgroundMusic.isPlaying()) {
                    backgroundMusic.start();
                }
            }
        });

        Button offButton = findViewById(R.id.off);
        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (backgroundMusic != null && backgroundMusic.isPlaying()) {
                    backgroundMusic.pause();
                }
            }
        });


        SeekBar seekBar = findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(VOLUME_PROGRESS_KEY, progress);
                editor.apply();


                float volume = (float) progress / 100;
                backgroundMusic.setVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseBackgroundMusic();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopBackgroundMusic();
    }


    private void pauseBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isPlaying()) {
            backgroundMusic.pause();
        }
    }


    private void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
            backgroundMusic.release();
            backgroundMusic = null;
        }
    }
}


