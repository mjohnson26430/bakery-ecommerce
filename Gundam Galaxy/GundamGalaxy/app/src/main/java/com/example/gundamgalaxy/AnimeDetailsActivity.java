package com.example.gundamgalaxy;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnimeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anime_details);


        String title = getIntent().getStringExtra("title");
        int imageResource = getIntent().getIntExtra("image", 0);
        double price = getIntent().getDoubleExtra("animePrice", 0.0);

        String synopsis = getIntent().getStringExtra("synopsis");


        setTitle(title);
        ImageView imageView = findViewById(R.id.ShowDetails);
        imageView.setImageResource(imageResource);

        TextView priceTextView = findViewById(R.id.Price);
        priceTextView.setText("Price: $ " + price);



        TextView synopsisTextView = findViewById(R.id.Synopsis);
        synopsisTextView.setText("Synopsis: " + synopsis);
    }
}
