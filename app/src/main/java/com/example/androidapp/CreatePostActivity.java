package com.example.androidapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreatePostActivity extends AppCompatActivity {

    EditText createTitle, createDescription;
    Button createPost;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;


    @SuppressLint("WrongViewCast")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createpost_activity);

        createTitle = findViewById(R.id.createTitle);
        createDescription = findViewById(R.id.createDescription);
        createPost = findViewById(R.id.createPost);

        createPost.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                createPost();
            }
        });
    }

    public void createPost(){
        firebaseDatabase = FirebaseDatabase.getInstance("https://androidapp-b2218-default-rtdb.europe-west1.firebasedatabase.app");
        reference = firebaseDatabase.getReference("posts");

        String title = createTitle.getText().toString();
        String description = createDescription.getText().toString();

        String userId = reference.push().getKey();
        CreatePostStore createPostStore = new CreatePostStore(title, description);

        reference.child(userId).setValue(createPostStore);

    }

}
