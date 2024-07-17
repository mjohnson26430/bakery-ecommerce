package com.example.namequiz.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.namequiz.FunFactsActivity;
import com.example.namequiz.R;

public class MainMenu extends AppCompatActivity {

    private static MediaPlayer backgroundMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        TextView settingTextView = findViewById(R.id.setting);
        TextView startTextView = findViewById(R.id.start);
        TextView exitTextView = findViewById(R.id.exit);
        TextView searchTextView = findViewById(R.id.leaderboard);
        TextView factTextView = findViewById(R.id.funfacts);

        if (backgroundMusic == null || !backgroundMusic.isPlaying()) {
            backgroundMusic = MediaPlayer.create(this, R.raw.aloelite);
            backgroundMusic.setLooping(true);
            backgroundMusic.start();
        }

        settingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, AudioActivity.class);
                startActivity(intent);
            }
        });

        startTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, MainActivity.class);
                startActivity(intent);
            }
        });

        searchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, LeaderboardActivity.class);
                startActivity(intent);
            }
        });

        factTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, FunFactsActivity.class);
                startActivity(intent);
            }
        });

        exitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(v.getContext());
                View customView = inflater.inflate(R.layout.dialog_exit, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setView(customView)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog dialog = builder.create();

                dialog.setOnShowListener(dialogInterface -> {
                    dialog.getWindow().setLayout(800, 600);
                });

                dialog.show();
            }
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

