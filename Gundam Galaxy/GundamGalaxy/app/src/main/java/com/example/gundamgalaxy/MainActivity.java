package com.example.gundamgalaxy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.gundamgalaxy.adapters.CategoryAdapter;
import com.example.gundamgalaxy.models.GundamModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private int currentPage = 0;
    private int NUM_PAGES = 3;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);

        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);

        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable update = new Runnable() {
            public void run() {
                if (currentPage < Integer.MAX_VALUE) {
                    currentPage++;
                } else {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 3000, 3000);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Kits", R.drawable.kits, "kits"));
        categoryList.add(new Category("Anime", R.drawable.anime, "anime"));
        categoryList.add(new Category("Games", R.drawable.games, "games"));
        categoryList.add(new Category("Wearables", R.drawable.wearables, "wearables"));

        CategoryAdapter adapter = new CategoryAdapter(this, categoryList);
        GridView gridView = findViewById(R.id.gridview);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             Category selectedCategory = categoryList.get(position);
                openGundamRv(selectedCategory.getId());
                Toast.makeText(MainActivity.this, "Selected Category: " + selectedCategory.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }

        if (viewPager != null) {
            viewPager.setAdapter(null);
        }
    }

    private void openGundamRv(String categoryId) {
        Intent intent = new Intent(this, GundamRv.class);
        intent.putExtra("id", categoryId);
        startActivity(intent);
    }
    private class MyViewPagerAdapter extends PagerAdapter {

        private int[] images = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};
        private String[] texts = {"Gundam: Requiem For Vengeance Release Date And Cast", "New Bandai 1/144 EG RX-78-2 Gundam kit Releases Friday", " Anouk Wipprecht Launches 3D Printed Clothing Line"};

        @Override
        public int getCount() {
            return images.length; // Number of pages
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View itemView = LayoutInflater.from(container.getContext()).inflate(R.layout.item_viewpager, container, false);

            ImageView imageView = itemView.findViewById(R.id.imageView);
            TextView textView = itemView.findViewById(R.id.textView);


            int imageIndex = position % images.length;
            int textIndex = position % texts.length;

            imageView.setImageResource(images[imageIndex]);
            textView.setText(texts[textIndex]);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
