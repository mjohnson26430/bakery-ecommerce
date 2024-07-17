package com.example.gundamgalaxy;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;

public class Navbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tab_collections);





        TabLayout.Tab tabCollections = tabLayout.newTab();
        tabCollections.setText("Collections");
        tabLayout.addTab(tabCollections);

        TabLayout.Tab tabCosplay = tabLayout.newTab();
        tabCosplay.setText("Cosplay");
        tabLayout.addTab(tabCosplay);

        TabLayout.Tab tabShop = tabLayout.newTab();
        tabShop.setText("Shop");
        tabLayout.addTab(tabShop);
    }
}
