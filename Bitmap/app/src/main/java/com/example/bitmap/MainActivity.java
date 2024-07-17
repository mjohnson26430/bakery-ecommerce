package com.example.bitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ImageView button = findViewById(R.id.button);
        final ImageView imageView = findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = getBitmapFromViewUsingCanvas(view);
                imageView.setImageBitmap(bitmap);
            }
        });

    }

    private Bitmap getBitmapFromViewUsingCanvas(View view) {
        // Create a new Bitmap object with the desired width and height
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);

        // Create a new Canvas object using the Bitmap
        Canvas canvas = new Canvas(bitmap);

        // Draw the View into the Canvas
        view.draw(canvas);

        // Return the resulting Bitmap
        return bitmap;
    }

}