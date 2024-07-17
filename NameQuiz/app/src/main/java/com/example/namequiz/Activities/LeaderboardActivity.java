package com.example.namequiz.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.namequiz.LeaderboardItem;
import com.example.namequiz.R;
import com.example.namequiz.RvAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RvAdapter adapter;
    private List<LeaderboardItem> leaderboardItems = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        recyclerView = findViewById(R.id.rvLeaderboard);
        progressBar = findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RvAdapter(this, leaderboardItems);
        recyclerView.setAdapter(adapter);

        initializeLeaderboard();
    }

    private void initializeLeaderboard() {
        progressBar.setVisibility(View.VISIBLE);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("leaderboard");
        database.orderByChild("score").limitToLast(10).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                leaderboardItems.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    LeaderboardItem item = snapshot.getValue(LeaderboardItem.class);
                    leaderboardItems.add(item);
                }
                Collections.reverse(leaderboardItems);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(LeaderboardActivity.this, "Failed to load leaderboard. Error: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
