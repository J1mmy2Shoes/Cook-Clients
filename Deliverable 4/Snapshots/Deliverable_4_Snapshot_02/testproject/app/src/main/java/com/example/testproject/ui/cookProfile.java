package com.example.testproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testproject.R;

public class cookProfile extends AppCompatActivity {
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        backButton = findViewById(R.id.backProfileBtn);

        displayInfo();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cookProfile.this, MainCook.class);
                intent.putExtra("Id", cookId);
                startActivity(intent);

            }

        });

    }

    public void displayInfo(){
        // Display cook info from database
        // TO DO
        return;
    }
}