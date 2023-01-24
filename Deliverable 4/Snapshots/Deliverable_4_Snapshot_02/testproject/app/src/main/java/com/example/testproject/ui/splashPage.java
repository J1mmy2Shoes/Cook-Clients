package com.example.testproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class splashPage extends AppCompatActivity {

    private Button logoutButton;
    private Button searchButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);
        Intent intent = getIntent();
        String typeOfUser = intent.getStringExtra("key");


        searchButton = findViewById(R.id.SearchBtn);

        logoutButton = findViewById(R.id.logoutBtn2);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent backOut = new Intent(splashPage.this, MainPage.class);
                backOut.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                FirebaseAuth.getInstance().signOut();
                startActivity(backOut);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent backOut = new Intent(splashPage.this, MealSearchDisplay.class);
                backOut.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(backOut);

            }
        });



    }
}