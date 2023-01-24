package com.example.testproject.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class cookProfile extends AppCompatActivity {
    DatabaseReference databaseRef;
    private Button backButton;
    String cookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_profile);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            cookId = null;
        } else {
            cookId = extras.getString("Id");
        }

        databaseRef = FirebaseDatabase.getInstance().getReference();

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        backButton = findViewById(R.id.backProfileBtn);

        databaseRef.child("users").child(cookId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String firstName = (String) snapshot.child("firstName").getValue();
                String lastName = (String) snapshot.child("lastName").getValue();
                String name = (firstName + " " + lastName);

                String address = (String) snapshot.child("address").getValue();
                String description = (String) snapshot.child("description").getValue();
                String email = (String) snapshot.child("email").getValue();
                double rating = (Long) snapshot.child("rating").getValue();


                final TextView nameField = (TextView) findViewById(R.id.profileName);
                nameField.setText(name);

                final TextView emailField = (TextView) findViewById(R.id.profileEmail);
                emailField.setText(email);

                final TextView addressField = (TextView) findViewById(R.id.profileAddress);
                addressField.setText(address);

                final TextView descField = (TextView) findViewById(R.id.descProfile);
                descField.setText(description);

                final TextView ratingField = (TextView) findViewById(R.id.ratingProfile);

                if (rating > 0) {
                    ratingField.setText(Double.toString(rating));
                } else{
                    ratingField.setText("0.0");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cookProfile.this, MainCook.class);
                intent.putExtra("Id", cookId);
                startActivity(intent);

            }

        });

    }
}