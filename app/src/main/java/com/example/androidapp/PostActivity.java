package com.example.androidapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PostActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_activity);

        checkCurrentUser();

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

    String postTitle, postDescription;

    public  String getPostTitle(){
        return postTitle;
    }

    public String getPostDescription(){
        return postDescription;
    }
}
