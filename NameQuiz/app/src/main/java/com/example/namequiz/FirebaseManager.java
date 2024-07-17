package com.example.namequiz;

import android.util.Log;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseManager {

    public static void writeNewUser(String userId, String name, int score) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("leaderboard");

        LeaderboardItem user = new LeaderboardItem(name, score);
        myRef.child(userId).setValue(user)
                .addOnSuccessListener(aVoid -> Log.d("Firebase", "Data written successfully!"))
                .addOnFailureListener(e -> Log.d("Firebase", "Failed to write data", e));
    }
}
