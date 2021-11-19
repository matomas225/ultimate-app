package com.example.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    ListAdapter listAdapter;
    ArrayList<PostActivity> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        checkCurrentUser();

        recyclerView = findViewById(R.id.list);
        database = FirebaseDatabase.getInstance("https://androidapp-b2218-default-rtdb.europe-west1.firebasedatabase.app/").getReference("posts");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        listAdapter = new ListAdapter(this, list);
        recyclerView.setAdapter(listAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    PostActivity postActivity = new PostActivity();
                    postActivity.title = dataSnapshot.child("title").getValue().toString();
                    postActivity.description = dataSnapshot.child("description").getValue().toString();
                    list.add(postActivity);
//                    PostActivity postActivity = dataSnapshot.getValue(PostActivity.class);
//                    list.add(postActivity);


                }

                listAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    public void checkCurrentUser() {
        // [START check_current_user]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
        } else {
            // No user is signed in
            startActivity(new Intent(this, MainActivity.class));
        }
        // [END check_current_user]
    }

}
