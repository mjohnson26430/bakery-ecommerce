package com.example.firebase;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference parentReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        firebaseDatabase = FirebaseDatabase.getInstance();
        parentReference = firebaseDatabase.getReference("MyDatabase");

        parentReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<User> userList = new ArrayList<>();
                ArrayList<User> studentList = new ArrayList<>();
                ArrayList<User> staffList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.child("user").getChildren()){
                    userList.add(dataSnapshot.getValue(User.class));
                }
                for (DataSnapshot dataSnapshot : snapshot.child("student").getChildren()){
                    studentList.add(dataSnapshot.getValue(User.class));
                }
                for (DataSnapshot dataSnapshot : snapshot.child("staff").getChildren()){
                    staffList.add(dataSnapshot.getValue(User.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}