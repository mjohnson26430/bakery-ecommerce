package com.example.gallery;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class ImageDetailActivity extends AppCompatActivity {

    private ArrayList<String> imagePaths;
    private int currentPosition;
    private ImageView imageView;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        imagePaths = getIntent().getStringArrayListExtra("imagePaths");
        currentPosition = getIntent().getIntExtra("position", 0);

        imageView = findViewById(R.id.idIVImage);
        gestureDetector = new GestureDetector(this, new GestureListener());

        loadImage();

        imageView.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));
    }

    private void loadImage() {
        if (currentPosition >= 0 && currentPosition < imagePaths.size()) {
            String imgPath = imagePaths.get(currentPosition);
            File imgFile = new File(imgPath);
            if (imgFile.exists()) {
                Picasso.get().load(imgFile).placeholder(R.drawable.ic_launcher_background).into(imageView);
            }
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();

                if (Math.abs(diffX) > Math.abs(diffY) && Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        // Swipe right
                        if (currentPosition > 0) {
                            currentPosition--;
                            loadImage();
                        }
                    } else {
                        // Swipe left
                        if (currentPosition < imagePaths.size() - 1) {
                            currentPosition++;
                            loadImage();
                        }
                    }
                    return true;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return false;
        }
    }
}
