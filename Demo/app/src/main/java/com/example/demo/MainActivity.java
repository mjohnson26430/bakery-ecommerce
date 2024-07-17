package com.example.demo;

import android.os.Bundle;
import android.widget.Toast;

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
    DatabaseReference studentReference;
    DatabaseReference staffReference;
    DatabaseReference userReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        firebaseDatabase = FirebaseDatabase.getInstance();
        parentReference = firebaseDatabase.getReference("MyDatabase");

        userReference = parentReference.child("user");
        studentReference = parentReference.child("student");
        staffReference = parentReference.child("staff");
        User user = null;
        ArrayList<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            user = new User();
            user.setUserName("Jeyaseelan "+i+1);
            user.setUserId(1+i);
            user.setAge(20+i);
            list.add(user);
        }
        userReference.setValue(list);

        ArrayList<User> studentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            user = new User();
            user.setUserName("Antony "+i+1);
            user.setUserId(1+i);
            user.setAge(17);
            studentList.add(user);
        }
        studentReference.setValue(studentList);

        ArrayList<User> staffList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            user = new User();
            user.setUserName("Martin "+i+1);
            user.setUserId(1+i);
            user.setAge(30);
            staffList.add(user);
        }
        staffReference.setValue(staffList);
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