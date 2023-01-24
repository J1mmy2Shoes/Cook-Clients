package com.example.deliverable1userinterface.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deliverable1userinterface.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainPage extends AppCompatActivity {
    Button logInBtn, signUpBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth.getInstance().signOut(); // Makes sure that the user is not currently signed in

        logInBtn = findViewById(R.id.login_button);
        signUpBtn = findViewById(R.id.signup_button);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(MainPage.this, LoginPage.class));
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(MainPage.this, RegisterPage.class));
            }
        });
    }

}